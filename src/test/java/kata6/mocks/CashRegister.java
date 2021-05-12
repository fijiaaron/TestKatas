package kata6.mocks;

public class CashRegister
{
	Order order = null;
	ScannerService scanner;
	PrinterService printer;

	public CashRegister(ScannerService scanner, PrinterService printer)
	{
		this.scanner = scanner;
		this.printer = printer;
	}

	public void addItem(String sku) throws ScannerService.ScannerException
	{
		Item item = scanner.lookup(sku); // may throw exception
		addItem(item);
	}

	public void addItem(Item item)
	{
		if (order == null)
		{
			order = new Order();
		}

		order.items.put(item, 1);
	}

	public Order getOrder()
	{
		return order;
	}

	public void printReceipt(Order order) throws PrinterService.PrinterException
	{
		printer.printReceipt(order); // may throw exception
	}

	public double pay(double payment)
	{
		Double change = payment - order.total();

		try
		{
			printReceipt(order);
		}
		catch (PrinterService.PrinterException e)
		{
			e.printStackTrace();;
		}

		order = null;
		return change;
	}
}
