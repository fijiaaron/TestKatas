package kata6.mocks;

import java.util.HashMap;

class Order
{
	HashMap<Item, Integer> items = new HashMap<>();

	public double total()
	{
		double subtotal = 0.0;

		for (Item item : items.keySet())
		{
			subtotal += item.price * items.get(item);
		}

		double discount = calculateDiscounts(subtotal);
		double tax = calculateTax(subtotal - discount);

		double total = subtotal - discount + tax;
		return total;
	}

	public double calculateDiscounts(double subtotal) {
		double discounts = 0.0;
		return subtotal - discounts;
	}

	public double calculateTax(double amount)
	{
		double taxrate = 0.0;
		return taxrate;
	}


}
