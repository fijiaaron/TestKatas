package kata5.exceptions;

import java.util.Arrays;

public class Calculator
{
	public int add(int a, int b)
	{
		return a+b;
	}

	public int subtract(int a, int b)
	{
		return a - b;
	}

	public int multiply(int a, int b)
	{
		return a * b;
	}

	public int divide(int a, int b)
	{
		return a/b;
	}

	public int sum(int...numbers)
	{
		int sum = 0;
		return Arrays.stream(numbers).reduce(sum, (a, b) -> add(a, b));
	}

	public int power(int a, int b)
	{
		return (int) Math.pow(a, b);
	}

	public int compute(String a, String operator, String b)
	{
		int ai = Integer.valueOf(a);
		int bi = Integer.valueOf(b);

		switch(operator)
		{
			case "+":
				return add(ai, bi);
			case "-":
				return subtract(ai, bi);
			case "*":
				return multiply(ai, bi);
			case "/":
				return divide(ai, bi);
			default:
				throw new UnsupportedOperationException("unsupported operator: " + operator);
		}
	}
}
