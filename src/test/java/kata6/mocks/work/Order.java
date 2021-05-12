package kata6.mocks.work;

import java.util.ArrayList;
import java.util.List;

public class Order
{
	public List<Item> items = new ArrayList<>();

	public double total() {
		return items.stream().map(item -> item.price).reduce(0.0, (subtotal, price) -> subtotal + price);
	}
}
