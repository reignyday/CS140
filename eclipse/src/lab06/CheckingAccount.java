package lab06;

public class CheckingAccount extends BankAccount
{
    private int withdrawLimit;
    private int withdrawCount = 0;

    public CheckingAccount(double bal, int limit)
    {
        super(bal);

        if (limit < 1)
            throw new IllegalArgumentException("withdrawal limit must be positive");

        this.withdrawLimit = limit;
    }

    @Override
    public double withdraw(double amt)
    {
        if (this.withdrawCount < this.withdrawLimit)
        {
            ++this.withdrawCount;
            
            return super.withdraw(amt);
        }
        return 0;
    }

    public int getWithdrawLimit()
    {
        return this.withdrawLimit;
    }

    public int getWithdrawCount()
    {
        return this.withdrawCount;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + this.withdrawCount + "/" + this.withdrawLimit;
    }
}
