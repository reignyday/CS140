package assignment06;

import java.math.BigInteger;

public class MultiplicationPersistence
{
    public static long digitProduct(long input)
    {
        var str = Long.toString(input);

        var chrs = str.toCharArray();

        long res = 1;

        for (var chr : chrs)
            res *= Long.parseLong(String.valueOf(chr));

        return res;
    }

    public static int numStepsToOneDigit(long n)
    {
        if (n < 10L)
            return 0;

        return 1 + numStepsToOneDigit(digitProduct(n));
    }

    public static BigInteger digitProduct(BigInteger input)
    {
        var str = input.toString();

        var chrs = str.toCharArray();

        BigInteger res = BigInteger.ONE;

        for (var chr : chrs)
            res = res.multiply(BigInteger.valueOf(Long.parseLong(String.valueOf(chr))));

        return res;
    }

    public static int numStepsToOneDigit(BigInteger n)
    {
        if (n.compareTo(BigInteger.TEN) < 0)
            return 0;

        return 1 + numStepsToOneDigit(digitProduct(n));
    }

    public static void main(String[] args)
    {
        System.out.println(numStepsToOneDigit(277777788888899L));
        System.out.println(numStepsToOneDigit(BigInteger.valueOf(277777788888899L)));
    }
}
