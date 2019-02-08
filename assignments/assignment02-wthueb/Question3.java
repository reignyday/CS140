package assignment02;

public class Question3
{
	private static void printLoanPackage(LoanPackage pkg)
	{
		System.out.println("amt: " + pkg.getAmount());
		System.out.println("ir: " + pkg.getInterestRate());
		System.out.println("years: " + pkg.getNumYears());
		System.out.println("due: " + pkg.getAmountDue());
	}

	public static void main(String[] argv)
	{
		var pkg1 = new LoanPackage(150, .2, 5);
		var pkg2 = new LoanPackage(100, .1, 3);

		System.out.println("pkg1:");
		printLoanPackage(pkg1);

		System.out.println("\npkg2:");
		printLoanPackage(pkg2);
	
		System.out.println("\nswapping fields\n");
		LoanPackage.exchangeFields(pkg1, pkg2);

		System.out.println("pkg1:");
		printLoanPackage(pkg1);

		System.out.println("\npkg2:");
		printLoanPackage(pkg2);

        var arr = new LoanPackage[5];
        arr[0] = new LoanPackage(100, 2, 3);
        arr[1] = new LoanPackage(130, 1, 5);
        arr[2] = new LoanPackage(400, .1, 10);
        arr[3] = new LoanPackage(300, .01, 30);
        arr[4] = new LoanPackage(150, .05, 20);

        System.out.println("\namounts due:");

        for (int i = 0; i < arr.length; ++i)
        {
            System.out.println(arr[i].getAmountDue());
        }

        System.out.println("\nfirst largest amount due index: " +
				LoanUtility.indexOfFirstLargestAmountDue(0, arr));

        LoanUtility.putInDescendingOrder(arr);

        System.out.println("\ndescending order:");

        for (int i = 0; i < arr.length; ++i)
            System.out.println(arr[i].getAmountDue());
	}
}
