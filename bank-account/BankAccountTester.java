package cs140;

public class BankAccountTester
{
	public static void main(String[] args)
	{
		BankAccount harrysChecking = new BankAccount();
		harrysChecking.deposit(2000);
		harrysChecking.withdraw(500);
		System.out.println(harrysChecking.getBalance());
		System.out.println("expected: 1500");
	}
}
