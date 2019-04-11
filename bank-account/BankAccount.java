package cs140;

public class BankAccount
{
    private double balance;
    
    public BankAccount()
    {
        balance = 0;
    }

    public BankAccount(double initial)
    {
        balance = initial;
    }

    public void deposit(double amt)
    {
        balance += amt;
    }

    public void withdraw(double amt)
    {
        balance -= amt;
    }

    public double getBalance()
    {
        return balance;
    }

    public double plan(int months, double deposited, double withdrew)
    {
        for (int i = 0; i < months; ++i)
        {
            deposit(deposited);
            withdraw(withdrew);
        }

        return getBalance();
    }
}
