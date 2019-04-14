package assignment06;

public class Filter extends Counter
{
    private int prime;

    private Counter next;

    public Filter(int prime, Counter next)
    {
        this.prime = prime;
        this.next = next;
    }

    @Override
    public void print()
    {
        this.next.print();

        System.out.println(this.prime);
    }

    @Override
    public int provideNext()
    {
        int i = this.next.provideNext();

        while (i % this.prime == 0)
            i = this.next.provideNext();

        return i;
    }
}
