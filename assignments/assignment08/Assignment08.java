package assignment08;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class Assignment08
{
    public static boolean isPalindrome(BigInteger input)
    {
        var str = "" + input;

        return new StringBuilder(str).reverse().toString().equals(str);
    }

    public static void generatePalindromicSquares()
    {
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.valueOf(1)))
                .map(n -> n.multiply(n))
                .filter(n -> isPalindrome(n))
                .limit(50)
                .forEach(System.out::println);
    }

    public static boolean isPrime(BigInteger n) {
        if (n == BigInteger.TWO || n == BigInteger.valueOf(3))
            return true;

        final BigInteger ZERO = BigInteger.ZERO;

        if (n.remainder(BigInteger.TWO) == ZERO || n.remainder(BigInteger.valueOf(3)) == ZERO)
            return false;

        // was having problems with using var here, not sure why
        final BigInteger ONE = BigInteger.ONE;
        final BigInteger SIX = BigInteger.valueOf(6);

        BigInteger k = ONE;
        BigInteger t1 = k.multiply(SIX).subtract(ONE);
        BigInteger t2 = k.multiply(SIX).add(ONE);

        while (t1.multiply(t1).compareTo(n) <= 0) {
            if (n.remainder(t1) == ZERO || n.remainder(t2) == ZERO)
                return false;

            k = k.add(ONE);
            t1 = k.multiply(SIX).subtract(ONE);
            t2 = k.multiply(SIX).add(ONE);
        }

        return true;
    }

    public static void generatePalindromicPrimes()
    {
        System.out.println(2);

        Stream.iterate(BigInteger.valueOf(3), n -> n.add(BigInteger.valueOf(2)))
                .filter(n -> isPalindrome(n))
                .filter(n -> isPrime(n))
                .limit(100)
                .forEach(System.out::println);
    }

    public static List<List<Long>> longFactPair(int n)
    {
        return Stream.iterate(List.of(0L, 1L), l -> {
            long e0 = l.get(0) + 1L;
            long e1 = l.get(1) * e0;

            return List.of(e0, e1);
        }).limit(n).collect(Collectors.toList());
    }

    static class Pair
    {
        int num;
        BigInteger factorial;

        public Pair(int n, BigInteger f)
        {
            this.num = n;
            this.factorial = f;
        }

        BigInteger getFactorial()
        {
            return this.factorial;
        }

        public String toString()
        {
            return "[" + this.num + ", " + this.factorial + "]";
        }
    }

    public static List<Pair> factPair(int n)
    {
        return Stream.iterate(new Pair(0, BigInteger.ONE), p -> {
            var num = p.num + 1;
            var fact = p.factorial.multiply(BigInteger.valueOf(num));

            return new Pair(num, fact);
        }).limit(n).collect(Collectors.toList());
    }

    public static List<BigInteger> factorials(int n)
    {
        return Stream.iterate(new Pair(0, BigInteger.ONE), p -> {
            var num = p.num + 1;
            var fact = p.factorial.multiply(BigInteger.valueOf(num));

            return new Pair(num, fact);
        }).limit(n).map(Pair::getFactorial).collect(Collectors.toList());
    }

    public static void main(String[] args)
    {
        System.out.println("isPalindrome(1234): " + isPalindrome(BigInteger.valueOf(1234)));
        System.out.println("isPalindrome(12321): " + isPalindrome(BigInteger.valueOf(12321)));

        generatePalindromicSquares();
        generatePalindromicPrimes();

        System.out.println(longFactPair(25));
        System.out.println(factPair(25));
        System.out.println(factorials(30));
    }
}
