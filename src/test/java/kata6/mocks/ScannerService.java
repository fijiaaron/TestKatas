package kata6.mocks;

public interface ScannerService
{
	Item lookup(String sku) throws ScannerException; // when it can't find item or read code
	class ScannerException extends Exception {}
}
