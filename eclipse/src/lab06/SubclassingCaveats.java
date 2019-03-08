package lab06;

public class SubclassingCaveats {
	public static void main(String[] args) {
		BankAccount b = new BankAccount(100.00);
		CheckingAccount c = new CheckingAccount(200.00, 3);
		SavingsAccount s = new SavingsAccount(500.00, 0.01);
		
		System.out.println("instanceof BankAccount?");
		System.out.println(b instanceof BankAccount);
		System.out.println(s instanceof BankAccount);
		System.out.println(c instanceof BankAccount);
		
		//TODO: why do lines 18 and 22 not compile?
		System.out.println("\ninstanceof SavingsAccount?");
		System.out.println(b instanceof SavingsAccount);
		System.out.println(s instanceof SavingsAccount);
		//System.out.println(c instanceof SavingsAccount);
		
		System.out.println("\ninstanceof CheckingAccount?");
		System.out.println(b instanceof CheckingAccount);
		//System.out.println(s instanceof CheckingAccount);
		System.out.println(c instanceof CheckingAccount);
		
		System.out.println("\ninstanceof Object?");
		System.out.println(b instanceof Object);
		System.out.println(s instanceof Object);
		System.out.println(c instanceof Object);
		
		System.out.println("\nUpcasting: from children to parent");
		//TODO: does this compile? any runtime exceptions?
		
		//b = new SavingsAccount(200.00, .15);
		//b = new CheckingAccount(150.00 , 5);
		//b = s;
		//b = c;
		
		System.out.println("\nDown casting: from parent to children");
		//TODO: does this compile? any runtime exceptions?
		
		//c = (CheckingAccount) b;
		//s = (SavingsAccount) b; 
		
		//TODO: does this compile? any runtime exceptions?
		
		//BankAccount acc = new CheckingAccount(100.0, 3);
		//c = (CheckingAccount) acc;
		//acc = new SavingsAccount(560.00, .25);
		//s = (SavingsAccount) acc;

	}
}
