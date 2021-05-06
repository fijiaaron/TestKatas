# Daily Test Kata

## Kata 5 - Negative Cases

It's important to write tests that make things work as expected, but we also need to make sure that the system behaves correctly under unexpected conditions.

In our code we try to catch and handle exception cases -- things that we don't expect to happen, but that can be catastrophic if they do -- like inputs that don't make sense, network or operating system or file system problems, or whatever.

We write code like this:

```java
try
{
    toDoSomethingUseful();
}
catch (UnpexpectedException e)
{
    handleIssue();
}
```

Often times, we can't do anything other than log the issue and report the problem.

But ideally we should have a recovery path.

```
try
{
    readFromFile();
    doSomething();
    writeToFile();
}
catch (FileNotFoundException e)
{
    log("can't read from file");
    e.printStackTrace();
}
catch (AccessDeniedException e)
{
    log("can't write to file")
    e.printStackTrace();
}
finally
{
    doSomethingElse();
}
```

In this case, if we can't read from a file, we have one problem, if we can't write to the file, we have another problem, but in either case, we want to take care of the problem, notify the user, and then cleanup and continue application flow.

We want to test that our program can handle all these cases.

 Now, let's consider our Calculator from the last exercise.

 [Calculator.java](./Calculator.java)

```java
	public int divide(int a, int b)
	{
		return a/b;
	}
```

There is a possible exception in this function.  Consider the following:

```java
Calculator calculator = new Calculator();
calculator.divide(1, 0);
```

You will get `java.lang.ArithmeticException: / by zero`

We want to test for this -- that the exception is thrown.  Here's how you can do this:

With Jnit 4, using an expected exception in the @Test annotation

[CalculatorTests.java with Junit 4](./junit4/CalculatorTests.java)
```java
@Test(expected = ArithmeticException.class)
public void testDivideByZero()
{
    Calculator calculator = new Calculator();
    int result = calculator.divide(1, 0);
}
```

In Jnit 5, you can catch the exception in your code with a lambda expression

[CalculatorTests.java with Junit5](./jnit5/CalculatorTests.java)
```java
@Test
public void shouldThrowExceptionWhenDividingByZero()
{
    Calculator calculator = new Calculator();


    Exception exception = assertThrows(ArithmaticException.class, () -> {
        calculator.divide(1, 0);
    });

    exception.printStackTrace();
}
```

Look at the Calculator, we've added some more functions to sum multiple numbers (e.g. a + b + c), to compute exponents, and to accept generic expressions:

```java
int total = calculator.sum(1, 2, 3);  // expect 6
```

```java
int squared = calculator.power(10, 2); // expect 100
```

```java
difference = calculator.compute("5", "-", "3") // expect 2
```

Can you write tests that will test expected and unexpected conditions like we've just done with divide by zero?


