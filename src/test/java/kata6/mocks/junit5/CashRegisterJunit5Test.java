package kata6.mocks.junit5;

import kata6.mocks.CashRegister;
import kata6.mocks.Item;
import kata6.mocks.PrinterService;
import kata6.mocks.ScannerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CashRegisterJunit5Test
{
	@InjectMocks
	CashRegister cashRegister;

	@Mock
	ScannerService scannerService;

	@Mock
	PrinterService printerService;

	Item milk = new Item("milk", 3.49, "123");
	Item cookies = new Item("cookies", 2.98, "456");

	@Test
	public void shouldAddItem()
	{
		cashRegister.addItem(milk);
	}

	@Test
	public void shouldAddItemBySKU() throws ScannerService.ScannerException
	{
		cashRegister.addItem(cookies.sku);
	}

	@Test
	public void shouldHandleScannerFailure()
	{

	}

	@Test
	public void shouldCalculateTotal()
	{}

	@Test
	public void shouldPrintReceiptOnComplete() throws PrinterService.PrinterException
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
