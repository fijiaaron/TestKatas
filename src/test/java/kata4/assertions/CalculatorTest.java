package kata4.assertions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest
{
	@Test
	public void shouldAddTwoNumbers()
	{
		Calculator calculator = new Calculator();

		int result = calculator.add(1, 2);

		assertEquals("3", result);
	}
}
