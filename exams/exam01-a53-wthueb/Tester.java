package exam01;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Tester {
	private static int numWrong;

	//-----------------------------------------------------------------------------------------
	//THESE METHODS ABOVE THE MAIN ARE NEEDED DO NOT COMMENT THEM OUT OR ALTER THEM 
	private static void testEquality(Object expected, Object actual) {
		if (!expected.toString().equals(actual.toString())) {
			++numWrong;
			System.out.printf("FAILED: Expected '%s', received '%s'\n",
					expected.toString(), actual.toString());
		} else {
			System.out.printf("Passed: '%s' == '%s'\n",
					expected.toString(),
					actual.toString());
		}
	}

	private static void testEquality(int[] expected, int[] actual) {
		testEquality(Arrays.toString(expected), Arrays.toString(actual));
	}

	private static void testEquality(double expected, double actual) {
		DecimalFormat df = new DecimalFormat("###.##");
		testEquality(df.format(expected), df.format(actual));
	}
	//-------------------------------------------------------------------------------------------

	public static void main(String[] args) {
		testQ1();
		System.out.println("--------------------");
		testQ2();
		System.out.println("--------------------");
		testQ3();
		System.out.println("--------------------");
		System.out.println(numWrong + " tests failed");
	}

	public static void testQ1() {
		System.out.println("*********TESTING QUESTION 1 (Worth 3 points)********\n");
		int[] arr1 = new int[]{-1,-20,-89,-89,-89,-15,-1,-15, -89};
		int[] arr2 = new int[]{1000,3,1000,5,1,-7,5,1000,1000,1000,1000,999,-7,-7};
		int[] arr3 = new int[]{1,1,2,3,4,5,5,5,5};

		testEquality(new int[]{2}, ArrayUtil.countMins(arr3));
		testEquality(new int[]{4, 3, 2}, ArrayUtil.countMins(arr1, arr2, arr3));

	}

	public static void testQ2() {
		System.out.println("*********TESTING QUESTION 2 (Worth 3 points)********\n");
		List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(2,5,7,8,11));

		testEquality(new ArrayList<Integer>(Arrays.asList(14,13,12,11,10)), ListUtil.exclusiveSum(list1));
		testEquality(new ArrayList<Integer>(Arrays.asList(31,28,26,25,22)), ListUtil.exclusiveSum(list2));
	}

	public static void testQ3() {
		System.out.println("*********TESTING QUESTION 3 (Worth 4 points)********\n");
		System.out.println("WARNING: Add testQ3()'s body back in when you are done with Question 3 and comment me out");
		
		Directory dir = new Directory();
		
		Person p1 = new Person("Jack", "Sparrow");
		Person p2 = new Person("Johnny", "Cash");
		Person p3 = new Person("Elvis", "Presley");
		Person p4 = new Person("Alexander", "Hamilton");
		Person p5 = new Person("Rosa", "Parks");
		Person p6 = new Person("Elizabeth", "Browning");
		Person p7 = new Person("Harriet", "Tubman");
		
		dir.addPerson(p1);
		dir.addPerson(p2);
		dir.addPerson(p3);
		dir.addPerson(p4);
		dir.addPerson(p5);
		dir.addPerson(p6);
		dir.addPerson(p7);

		testEquality("Rosa Tubman", dir.latestName());
		testEquality("[Jack Sparrow, Johnny Cash, Elvis Presley, Alexander Hamilton, Rosa Parks, Elizabeth Browning, Harriet Tubman]", dir);
		
		dir.makeAFamily("Green");
		
		testEquality("[Jack Green, Johnny Green, Elvis Green, Alexander Green, Rosa Green, Elizabeth Green, Harriet Green]", dir);
	}
}
