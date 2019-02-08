package assignment02;

public class Question4
{
	public static void main(String[] argv)
	{
		System.out.println("----- Loanee1 testing -----");

		var loanee = new Loanee1(100, "Will");

		System.out.println("loanee name: " + loanee.getName());
		
		System.out.println("current money (expect 100): " + loanee.getMoney());
		
		loanee.addMoney(50);

		System.out.println("current money (expect 150): " + loanee.getMoney());
		
		loanee.setLoanPackage(100, .2, 5);

		System.out.println("amount due (expect 248.83): " + loanee.getLoanAmount());

		System.out.println("\n----- LoanUtility1 testing -----");

        var arr = new Loanee1[5];

		arr[0] = new Loanee1(100, "Steve");
		arr[1] = new Loanee1(150, "Jim");
		arr[2] = new Loanee1(200, "Bob");
		arr[3] = new Loanee1(300, "Dave");
		arr[4] = new Loanee1(400, "Jack");

        arr[0].setLoanPackage(100, .1, 5);
        arr[1].setLoanPackage(130, 1, 5);
        arr[2].setLoanPackage(400, .1, 10);
        arr[3].setLoanPackage(300, .01, 30);
        arr[4].setLoanPackage(150, .05, 20);

        System.out.println("loan amounts:");

        for (int i = 0; i < arr.length; ++i)
        {
            System.out.println(arr[i].getLoanAmount());
        }

        System.out.println("\nfirst largest amount loan index: " +
				LoanUtility1.indexOfFirstLargestLoan(0, arr));

        LoanUtility1.putInDescendingOrder(arr);

        System.out.println("\ndescending order:");

        for (int i = 0; i < arr.length; ++i)
            System.out.println(arr[i].getLoanAmount());
	}
}
