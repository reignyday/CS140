package lab06;

public class BankAccount
{
	private double balance;
	private int idNum;
	private static int numAccounts = 0;
	
	static void reset()
	{
		BankAccount.numAccounts = 0;
	}

	public BankAccount(double bal)
	{
		if (bal < 0)
			throw new IllegalArgumentException("balance cannot be negative");

		this.balance = bal;

		this.idNum = BankAccount.numAccounts++;
	}
	
	public void deposit(double amt)
	{
		// should be <= but that wasn't requested sooo...
		if (amt < 0)
			throw new IllegalArgumentException("amount deposited has to be a positive number");
		
		this.balance += amt;
	}
	
	public double withdraw(double amt)
	{
		if (amt < 0)
			throw new IllegalArgumentException("amount withdrawn must be a positive number");
		
		if (amt > this.balance)
		{
			var withdrawn = this.balance;
			
			this.balance = 0;
			
			return withdrawn;
		}
		
		this.balance -= amt;
		
		return amt;
	}

	public double getBalance()
	{
		return this.balance;
	}

	public int getIdNum()
	{
		return this.idNum;
	}
	
	@Override
	public String toString()
	{
		return "Account #" + this.idNum + " has $" + this.balance;
	}
}
