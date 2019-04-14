package assignment06;

import java.util.List;
import java.util.ArrayList;

public class StairCase
{
    public static List<List<Integer>> stepPaths(int n)
    {
        if (n < 1)
            throw new IllegalArgumentException("number of steps must be >= 1");

        var paths = helper(n-1);

        for (int i = 0; i < paths.size(); ++i)
        {
            var path = paths.get(i);

            paths.remove(path);

            var steps = new ArrayList<Integer>();

            int count = 1;

            steps.add(count);

            for (var diff : path)
            {
                count += diff;

                steps.add(count);
            }

            paths.add(i, steps);
        }

        return paths;
    }

    private static List<List<Integer>> helper(int n)
    {
        List<List<Integer>> paths = new ArrayList<>();

        if (n < 1)
        {
            paths.add(new ArrayList<>());

            return paths;
        }

        if (n == 1)
        {
            var l = new ArrayList<Integer>();

            l.add(1);

            paths.add(l);

            return paths;
        }

        for (var path : helper(n - 1))
        {
            path.add(0, 1);

            paths.add(path);
        }

        for (var path : helper(n - 2))
        {
            path.add(0, 2);

            paths.add(path);
        }

        return paths;
    }
}
