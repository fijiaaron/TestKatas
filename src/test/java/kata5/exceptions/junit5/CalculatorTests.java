package kata5.exceptions.junit5;

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

	}
}
