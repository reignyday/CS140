package assignment06;

public class Driver
{
    public static Filter buildFilterList(int limit)
    {
        var c = new Counter();

        var f = new Filter(2, c);

        int p = f.provideNext();

        while (p < limit)
        {
            p = f.provideNext();

            f = new Filter(p, f);
        }

        return f;
    }

    public static void main(String[] args)
    {
        var f = buildFilterList(10000);

        f.print();
    }
}
