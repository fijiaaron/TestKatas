package kata6.mocks;

public interface PrinterService
{
	void printReceipt(Order order) throws PrinterException;
	class PrinterException extends Exception {}
}
