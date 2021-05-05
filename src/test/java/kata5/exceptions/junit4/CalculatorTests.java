package kata5.exceptions.junit4;

import kata5.exceptions.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTests
{

	@Test
	public void testAdd()
	{
		Calculator calculator = new Calculator();
		int result = calculator.compute("1", "+", "2");
		assertEquals(3, result);
	}

	@Test(expected = NumberFormatException.class)
	public void testInvalidNumber()
	{
		Calculator calculator = new Calculator();
		int result = calculator.compute("l", "+", "2");
		assertEquals(3, result);
	}
}
