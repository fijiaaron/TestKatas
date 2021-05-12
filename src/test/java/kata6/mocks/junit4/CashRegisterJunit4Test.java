package kata6.mocks.junit4;

import kata6.mocks.PrinterService;
import kata6.mocks.ScannerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CashRegisterJunit4Test
{
	@Test
	public void shouldAddItem()
	{}

	@Test
	public void shouldAddItemBySKU() throws ScannerService.ScannerException
	{}

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
