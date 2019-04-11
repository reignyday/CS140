package lab08;

import java.util.List;

public class Recursion
{
    private static final double DELTA = 1e-6;
    
    public static int factorial(int n)
    {
        if (n < 0)
            throw new IllegalArgumentException("cannot calculate a factorial of n < 0");
        
        if (n == 0)
            return 1;
        
        return n * factorial(n - 1);
    }
    
    public static double squareRoot(double n)
    {
        return squareRootHelper(n, n);
    }
    
    private static double squareRootHelper(double n, double approx)
    {
        if (Math.abs(n - approx*approx) < DELTA)
            return approx;
        
        double newApprox = (approx + n/approx) / 2;
        
        return squareRootHelper(n, newApprox);
    }
    
    public static int sum(List<Integer> nums)
    {
        return sumHelper(nums, 0);
    }
    
    private static int sumHelper(List<Integer> nums, int index)
    {
        if (index == nums.size())
            return 0;
        
        return nums.get(index) + sumHelper(nums, index+1);
    }
    
    public static int sum(int[] nums)
    {
        return sumHelper(nums, 0);
    }
    
    private static int sumHelper(int[] nums, int index)
    {
        if (index == nums.length)
            return 0;
        
        return nums[index] + sumHelper(nums, index+1);
    }
    
    public static int binomialCoefficient(int n, int k)
    {
        if (n < 0 || k < 0)
            throw new IllegalArgumentException("cannot have negative inputs");
        
        if (k == 0)
            return 1;
        
        if (k > n/2)
            return binomialCoefficient(n, n-k);
        
        return binomialCoefficient(n-1, k-1) + binomialCoefficient(n-1, k);
    }
}
