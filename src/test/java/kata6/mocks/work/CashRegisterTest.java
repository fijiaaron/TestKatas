package kata6.mocks.work;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

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
		assertEquals(item.name, expectedItem.name, "Item matched what was added to order");
	}

	@Test
	public void shouldCalculateCorrectChangeWhenPaying() throws PrinterService.PrinterException
	{
		ScannerService scannerMock = Mockito.mock(ScannerService.class);
		PrinterService printerMock = Mockito.mock(PrinterService.class);

		CashRegister cashRegister = new CashRegister(scannerMock, printerMock);
		cashRegister.addItem(milk);
		cashRegister.addItem(cookies);

		double change = cashRegister.pay(20.00);
		assertEquals(13.57, change);
	}
}
