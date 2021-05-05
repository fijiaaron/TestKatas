Daily Test Kata
=================

Kata 4 - Assertions
-------------------

Every test should have three parts:

1. Arrange
2. Act
3. Assert

Like the 3 acts to a play.

In a test, you should declare your system under test, supporting actors, describe their condition and behavior (e.g. with mocks) and and environment or data that will affect the test at the start.

So

Without the setup, which sets the scene and introduces the characters, a test does not make sense.

But without an assertion, it doesn't have any conclusion.  You're not really testing anything other than that the code executes.

This may satisfy code coverage checks, but it does not make a good test.  Like a good story, it's important to have a definite ending.

So let's take a look at a simple example of a Calculator class:

```java
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
		return a / b;
	}
}
```

How would you test this?

Here is a starter test:

```java
public class CalculatorTest
{
	@Test
	public void shouldAddTwoNumbers()
	{
        // Arrange -- we introduce our hero, the simple calculator
		Calculator calculator = new Calculator();

        // Act -- a challenge is presented, and the calculator leaps into action
		int result = calculator.add(1, 2);

        // Assert -- our happy conclusion
		assertEquals("3", result);
	}
}
```

Let's see if you can add some more tests.





