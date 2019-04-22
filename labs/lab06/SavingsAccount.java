package lab06;

public class SavingsAccount extends BankAccount
{
    private double rate;
    
    public SavingsAccount(double bal, double r)
    {
        super(bal);

        if (r < 0)
            throw new IllegalArgumentException("rate must be a positive number");
        
        this.rate = r;
    }

    @Override
    public void deposit(double amt)
    {
        var inflated = amt * (1.0 + this.rate);
        
        super.deposit(inflated);
    }
    
    public double getRate()
    {
        return this.rate;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " @ " + this.rate * 100 + "%";
    }
}
