package assignment04;

import java.util.PriorityQueue;

public class Utility
{
    private static class ArrayPair implements Comparable<ArrayPair> {
        public int[] array;
        public int index;
     
        public ArrayPair(int[] array, int index) {
            this.array = array;
            this.index = index;
        }
     
        @Override
        public int compareTo(ArrayPair o) {
            return this.array[this.index] - o.array[o.index];
        }
    }

    public static int[] concat(int[]... arrays)
    {
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
    // a few months ago. should be O(nk*logn)
    public static int[] merge(int[]... arrays)
    {
        // min binary heap
        var queue = new PriorityQueue<ArrayPair>();

        int size = 0;

        for (var array : arrays)
        {
            queue.add(new ArrayPair(array, 0));

            size += array.length;
        }

        var ret = new int[size];

        int i = 0;

        while (!queue.isEmpty())
        {
            var pair = queue.poll();

            ret[i++] = pair.array[pair.index];
            
            // this handles variable array lengths
            if (pair.index < pair.array.length - 1)
                queue.add(new ArrayPair(pair.array, pair.index + 1));
        }

        return ret;
    }
}
