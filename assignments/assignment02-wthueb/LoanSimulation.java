package assignment02;

import java.util.Arrays;

/**
 * A simulation class that tests some Loanee
 */
public class LoanSimulation {
    public static void main(String[] args) {
		var Loanee = new Loanee[3];

		for (int i = 0; i < Loanee.length; ++i)
		{
			Loanee[i] = new Loanee(100);
		}
		
		int[] amt = { 100, 100, 110 };
		double[] ir = { 0, .1, .1 };
		int[] yrs = { 10, 1, 1};

		var canpay = new boolean[3];

		for (int i = 0; i < Loanee.length; ++i)
		{
			canpay[i] = Loanee[i].canPayLoan(amt[i], ir[i], yrs[i]);
		}

		System.out.println(Arrays.toString(canpay));
    }
}
