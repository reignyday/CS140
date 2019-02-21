package lab05;

import java.util.List;
import java.util.ArrayList;

public class Division
{
    private List<Double> list = new ArrayList<>();

    public Division(double[] array)
    {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("need to enter an array with at least one double");

        for (var d : array)
            this.list.add(d);
    }

    public void removeZeroes()
    {
        for (int i = this.list.size() - 1; i >= 0; --i)
            if (this.list.get(i).equals(0.0))
                this.list.remove(i);
    }

    public void divide(int div)
    {
        if (div == 0)
            throw new IllegalArgumentException("cannot divide by zero");

        for (int i = 0; i < this.list.size(); ++i)
        {
            var val = this.list.get(i);

            val /= div;

            this.list.set(i, val);
        }
    }

    public String toString()
    {
        return this.list.toString();
    }
}
