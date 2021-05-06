package kata5.exceptions.junit5;

import kata4.assertions.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTests
{
	@Test
	public void shouldThrowExceptionWhenNonNumeric()
	{
		Exception exception = assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("1");
		});

		exception.printStackTrace();
	}

	@Test
	public void shouldThrowExceptionWhenDividingByZero()
	{
		Calculator calculator = new Calculator();
		calculator.divide(1, 0);

		Exception exception = assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("1");
		});

		exception.printStackTrace();

		exception.printStackTrace();
	}
}
