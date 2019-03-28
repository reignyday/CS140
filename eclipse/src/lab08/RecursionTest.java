package lab08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RecursionTest
{

	@Test
	void testFactorial()
	{
		try
		{
			Recursion.factorial(-5);
		}
		catch (Exception e)
		{
			System.out.println("illegal argument handled: " + e);
		}
		
		assertEquals(1, Recursion.factorial(0));
		assertEquals(1, Recursion.factorial(1));
		
		assertEquals(5040, Recursion.factorial(7));
	}

	@Test
	void testSquareRoot()
	{
		assertEquals(0, Recursion.squareRoot(0));
		assertEquals(1, Recursion.squareRoot(1));
		
		assertEquals(true, Math.abs(Recursion.squareRoot(16) - 4) < 1e-6);
	}

	@Test
	void testSumListOfInteger()
	{
		List<Integer> list = new ArrayList<>();
		
		list.add(3);
		list.add(72);
		list.add(61);
		
		assertEquals(136, Recursion.sum(list));
		
		List<Integer> empty = new ArrayList<>();
		
		assertEquals(0, Recursion.sum(empty));
	}

	@Test
	void testSumIntArray()
	{
		int[] arr = { 3, 72, 61 };
		
		assertEquals(136, Recursion.sum(arr));
		
		int[] empty = {};
		
		assertEquals(0, Recursion.sum(empty));
	}

	@Test
	void testBinomialCoefficient()
	{
		assertEquals(120, Recursion.binomialCoefficient(10, 3));
		assertEquals(10, Recursion.binomialCoefficient(10, 9));
	}

}
