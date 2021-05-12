package kata6.mocks.work;

public interface PrinterService
{
	void printReceipt(Order order) throws PrinterException; // e.g. when out of paper;
	class PrinterException extends Exception {}
}
