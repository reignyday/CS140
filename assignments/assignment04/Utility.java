package assignment04;

import java.util.PriorityQueue;

public class Utility
{
    private static class ArrayPair implements Comparable<ArrayPair>
    {
        public int[] array;
        public int index;
     
        public ArrayPair(int[] array, int index)
        {
            this.array = array;
            this.index = index;
        }

        @Override
        public int compareTo(ArrayPair o)
        {
            return this.array[this.index] - o.array[o.index];
        }
    }

    public static int[] concat(int[]... arrays)
    {
        if (arrays == null)
            return null;

        if (arrays.length == 0)
            return new int[0];

        int size = 0;

        for (var array : arrays)
            size += array.length;

        var ret = new int[size];
        
        int index = 0;

        // wish there was pointers :(
        for (var array : arrays)
            for (var n : array)
                ret[index++] = n;

        return ret;
    }

    // figured out how to do this efficiently while practicing interview questions
    // a few months ago. should be O(nlogk) -> n being total elements, k being arrays.length
    public static int[] merge(int[]... arrays)
    {
        if (arrays == null)
            return null;

        if (arrays.length == 0)
            return new int[0];

        // min binary heap
        // smallest element is always going to be at the head 
        var queue = new PriorityQueue<ArrayPair>();

        int size = 0;

        for (var array : arrays)
        {
            // pass first element of each array to queue
            queue.add(new ArrayPair(array, 0));

            size += array.length;
        }

        var ret = new int[size];

        int i = 0;

        while (!queue.isEmpty())
        {
            // get and remove head, guarenteed to be smallest
            var pair = queue.poll();

            ret[i++] = pair.array[pair.index];
            
            // pass next smallest element of each array to queue
            if (pair.index < pair.array.length - 1)
                queue.add(new ArrayPair(pair.array, pair.index + 1));
        }

        return ret;
    }
}
