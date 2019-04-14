package assignment06;

import java.util.Arrays;

public class ArrayRecursion
{
    public static boolean find(int[] array, int[] part)
    {
        if (array == null || part == null)
            throw new IllegalArgumentException("cannot search using null arrays");

        if (array.length == 0 || part.length == 0)
            throw new IllegalArgumentException("cannot search using empty arrays");

        if (part.length > array.length)
            return false;

        var found = true;

        for (int i = 0; i < part.length; ++i)
            if (part[i] != array[i])
                found = false;

        if (found)
            return true;

        if (array.length <= 1)
            return false;

        return find(Arrays.copyOfRange(array, 1, array.length), part);
    }

    public static int indexOf(int[] array, int[] part)
    {
        return indexOf(array, part, 0);
    }

    private static int indexOf(int[] array, int[] part, int index)
    {
        if (array == null || part == null)
            throw new IllegalArgumentException("cannot search using null arrays");

        if (part.length == 0)
            throw new IllegalArgumentException("cannot search for an empty array");

        if (array.length == 0)
            return -1;

        if (part.length > array.length)
            return -1;

        var found = index;
        var j = index;

        for (int i = 0; i < part.length; ++i)
            if (part[i] != array[j++])
                found = -1;

        if (found != -1)
            return found;

        if (array.length <= 1)
            return -1;

        return indexOf(array, part, ++index);
    }
}
