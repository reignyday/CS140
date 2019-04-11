package lab08;

public class Bonus
{
    public static void printPascalsTriangle(int limit)
    {
    	printPascalsHelper(limit, 0, 0);
    }
    
    private static void printPascalsHelper(int limit, int n, int k)
    {
    	if (n > limit)
    		return;
    	
    	if (n == k)
    	{
    		System.out.println(Recursion.binomialCoefficient(n, k));
    		
    		printPascalsHelper(limit, n+1, 0);
    		
    		return;
    	}
    	
    	System.out.print(Recursion.binomialCoefficient(n, k) + " ");
    	
    	printPascalsHelper(limit, n, k+1);
    }
    
    public static void main(String[] args)
    {
    	printPascalsTriangle(5);
    }
}
