package lab05;

public class Zipper
{
    public static int[] zip(int[] arr1, int[] arr2)
    {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length)
            throw new IllegalArgumentException("bad input");

        var ret = new int[arr1.length * 2];

        int j = 0;
        int k = 0;

        for (int i = 0; i < ret.length; ++i)
        {
            if (i % 2 == 0)
                ret[i] = arr1[j++];
            else
                ret[i] = arr2[k++];
        }

        return ret;
    }
}

