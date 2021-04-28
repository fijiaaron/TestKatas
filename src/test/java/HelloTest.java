import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloTest
{
	@Test
	public void testHelloWorld()
	{
		String text = "Hello, World!";

		assertEquals(text, "Hello, World!");
	}
}
