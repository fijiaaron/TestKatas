package kata1.gettingstarted;

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

	class HelloWorld
	{
		public void say()
		{
			System.out.println("Hello, world!");
		}
	}
}
