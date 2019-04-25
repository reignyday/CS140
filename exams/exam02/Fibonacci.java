package exam02;

public class Fibonacci implements Supplier
{
	private static int fib1 = 0;
	private static int fib2 = 1;
	
	@Override
	public int supplyNext()
	{
		int temp = Fibonacci.fib1 + Fibonacci.fib2;
		
		Fibonacci.fib1 = Fibonacci.fib2;
		Fibonacci.fib2 = temp;
		
		return temp;
	}
}
