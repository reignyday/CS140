package lab04;

public class Factorial{
	public static int factorial(int num){
		if(num < 0){
			throw new IllegalArgumentException("Cannot take factorial of negative number");
		}
		int product = 1;
		for(int i = 1; i<= num; i++) product *= i;
		return product;
	}

	public static void main(String[] args){
		System.out.println("4! = " + factorial(4));
		System.out.println("3! = " + factorial(3));
		System.out.println("2! = " + factorial(2));
		System.out.println("1! = " + factorial(1));
		System.out.println("0! = " + factorial(0));
		System.out.println("(-1)! = " + factorial(-1));
	}
}