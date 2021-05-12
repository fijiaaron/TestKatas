package kata6.mocks.work;

public class CashRegister
{
	Order order = null;
	ScannerService scanner;
	PrinterService printer;

	public CashRegister()
	{}

	public CashRegister(ScannerService scanner, PrinterService printer)
	{
		this.scanner = scanner;
		this.printer = printer;
	}

	public void addItem(Item item)
	{
		order.items.add(item);
	}

	public void addItem(String sku) throws ScannerService.ScannerException
	{
		Item item = scanner.lookup(sku);
		addItem(item);
	}

	public double getOrderTotal()
	{
		return order.total();
	}

	public double pay(double cash) throws PrinterService.PrinterException
	{
		double change = cash - order.total();
		printer.printReceipt(order);
		return change;
	}
}
