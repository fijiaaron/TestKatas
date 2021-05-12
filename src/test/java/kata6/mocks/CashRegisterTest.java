package kata6.mocks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CashRegisterTest
{
	// Test Data
	Item milk = new Item("milk", 3.49, "123");
	Item cookies = new Item("cookies", 2.98, "456");

	@Test
	public void shouldAddItems()
	{
		/** Arrange **/
		CashRegister cashRegister = new CashRegister(null, null);

		/** Act **/
		cashRegister.addItem(milk);
		cashRegister.addItem(cookies);

		/** Assert **/
		assertEquals(1, cashRegister.order.items.size());
	}

	@Test
	public void shouldCreateOrderWithFirstItem()
	{
		CashRegister cashRegister = new CashRegister(null, null);
		Item item = new Item("Milk", 3.59, "1234");
		cashRegister.addItem(item);
	}

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

		CashRegister cashRegister = new CashRegister(scannerMock, null);

		/** Act **/
		cashRegister.addItem(sku);

		/** Assert **/
		Order order = cashRegister.getOrder();
		assertNotNull(order, "order should have been created when item was added");
		assertFalse(order.items.isEmpty(), "order should not be empty");

		// get the items from our order
		Set<Item> items = order.items.keySet();
		assertEquals(1,items.size(), "there should be only 1 type of item in the order");

		// get the quantity of matching items
		assertEquals(1, order.items.get(expectedItem), "there should only be a quantity of 1 for type of item we added");

		// get our single item from the order
		Item item = items.iterator().next();
		assertEquals(item.sku, expectedItem.sku);
		assertEquals(item.name, expectedItem.name);
		assertEquals(item.price, expectedItem.price);
	}

	@Test
	public void shouldHandleItemLookupFailure()
	{

	}

	@Test
	public void shouldCalculateTotal()
	{}

	@Test
	public void shouldPrintReceiptOnComplete()
	{}

	@Test
	public void shouldHandlePrinterFailure()
	{}

	@Test
	public void shouldCompleteOrder()
	{}

	@Test
	public void shouldCompleteOrderWhenPrintReceiptFails()
	{}

}
