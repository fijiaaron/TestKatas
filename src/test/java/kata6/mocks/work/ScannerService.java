package kata6.mocks.work;


public interface ScannerService
{
	Item lookup(String sku) throws ScannerException; // e.g. when it can't find item or read code
	class ScannerException extends Exception {}
}
