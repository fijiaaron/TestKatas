Kata 6 - Using Mocks
--------------------

When you write unit tests, you want to be able to test a single object in isolation.  Hence the term "unit test".  There are a few reasons for doing this:

* You won't have to set up dependencies
* Tests will execute faster
* You will know that nothing outside the test affected the outcome

The last point is probably the most important, because we want our tests to be reliable.  We don't want them to intermittently fail based on some external factor, and if a test fails, we want to know exactly what our failure was so we can fix it quickly.

It can sometimes be difficult to test a single object in isolation.  You might find it easier if you could test the whole system -- and if you have a good continuous delivery setup, you'll be able to do that on every commit.

But, not only is that slower, it introduces a lot of potentially random variables -- network, file system, database, browser, external services, etc.

So you might consider writing integration tests that exercise the code, but don't use the whole system.  This can be done with a clean architecture (like Spring provides). The risk here is that when you encounter an issue in the dependencies, you won't know what caused the error.  And it may still take considerable effort to create all those dependencies.

There is another advantage to unit testing in isolation -- sometimes your dependencies haven't been implemented yet, and you don't want to wait until they are before starting your tests.

So let's take a look at an example like this. Today, we're going to test a Cash Register:

Here is our specification:

#### Cash Register
- can add items to an order
- can lookup an item by SKU from the scanner
- can get the order total
- can calculate change from a cash payment
- can print receipts when payment is complete


from [Cash Register](work/CashRegister.java)

```java
public class CashRegister
{
	Order order;
	ScannerService scanner;
	PrinterService printer;

	public void addItem(Item item)
	{
		order.items.add(item);
	}

	public void addItem(String sku) throws ScannerService.ScannerException
	{
		scanner.lookup(sku);
	}

	public double getOrderTotal()
	{
		return order.total();
	}

	public double pay(double cash)
	{
		double change = cash - order.total();
		return change;
	}

	public void printReceipt() throws PrinterService.PrinterException
	{
		printer.printReceipt(order);
	}
}
```

We will need to create objects for ***Item*** and ***Order***

[Item.java](./Item.java)
```java
public class Item {
	public String name;
	public double price;
	public String sku;
}
```


[Order.java](./Order.java)
```java
public class Order
{
	public List<Item> items = new ArrayList<>();

	public double total() {
	    double total = 0;
	    // TODO: calculate total
		return total;
	}
}
```

We can write a test that checks if our CashRegister will add items to an order:

[CashRegisterTest.java](work/CashRegisterTest.java)

```java
public class CashRegisterTest
{
	Item milk = new Item("milk", 3.49, "1234");
	Item cookies = new Item("cookies", 2.98, "5678");

	@Test
	public void shouldAddItemsToOrder()
	{
		CashRegister cashRegister = new CashRegister();
		cashRegister.addItem(milk);
		cashRegister.addItem(cookies);

		assertEquals(1, cashRegister.order.items.size());
		assertEquals("milk", cashRegister.order.items.get(0).name);
	}
}
```

But, in order to test that we can add items by just scanning the SKU, we need a scanner -- but we don't have a scanner implementation, just an interface:

[ScannerService.java](work/ScannerService.java)
```java
public interface ScannerService
{
	Item lookup(String sku) throws ScannerException; // when it can't find item or read code

	class ScannerException extends Exception {}
}
```

Likewise, we don't have a working printer:

```java
public interface PrinterService
{
	void printReceipt(Order order) throws PrinterException;
	class PrinterException extends Exception {}
}
```

How can we test this before these services are implemented?
That's where mocks come in.

Mocking allows you to inject a test double for your  dependencies.
So first, we need to be able to add our dependencies to the CashRegister.
We can do that by creating a constructor that passes them in:

```java
public class CashRegister
{
	ScannerService scanner;
	PrinterService printer;

	public CashRegister(ScannerService scanner, PrinterService printer)
	{
		this.scanner = scanner;
		this.printer = printer;
	}
}
```

Then we can create a mock Scanner that will just return an item when given a sku (without doing any external lookup!):

```java
import org.mockito.Mockito;

class CashRegisterTest
{
    //...

	@Test
	public void shouldAddItemByScanningSKU() throws ScannerService.ScannerException
	{
		/** Arrange **/
		// create test data
		String sku = "5678";
		Item expectedItem = new Item("Bread", 2.99, "5678");

		// setup scanner mock to always return the expected item
		ScannerService scannerMock = Mockito.mock(ScannerService.class);
		Mockito.when(scannerMock.lookup(sku)).thenReturn(expectedItem);

		// inject mock scanner into constructor -- note that we don't even need a printer for this test
		CashRegister cashRegister = new CashRegister(scannerMock, null);

		/** Act **/
		cashRegister.addItem(sku);

		/** Assert **/
		// make sure the item was added to the cart
		assertNotNull(cashRegister.order, "Order has been created");
		assertFalse(cashRegister.order.items.isEmpty(), "Item has been added to order");

		// make sure it added the right item
		Item item = cashRegister.order.items.get(0);
		assertEquals(expectedItem.name, item.name, "Item matched what was added to order");
	}
}
```

Note that we don't actually need a printer (or to create a mock printer service) for this test, although if we tried calling.

Now that we've written a test that can add an item using a mock ScannerService, try mocking PrinterService so we can test calculating the correct change:


```java
class CashRegisterTest
{
	//...
	@Test
	public void shouldCalculateCorrectChangeWhenPayingWithCash() throws PrinterService.PrinterException
	{
		// create mocks for ScannerService and PrinterService

		CashRegister cashRegister = new CashRegister(scannerMock, printerMock);
		cashRegister.addItem(milk);
		cashRegister.addItem(cookies);

		double change = cashRegister.pay(20.00);
		assertEquals(13.57, change);
	}
}
```
