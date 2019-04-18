package project;

import java.util.Arrays;

public class Memory
{
    public static final int DATA_SIZE = 512;

    private int[] data = new int[DATA_SIZE];

    int[] getData(int start, int end)
    {
        if (start < 0 || end >= DATA_SIZE)
            throw new DataAccessException(
                    String.format("Memory index OOB: %d-%d, [0, %d)", start, end, DATA_SIZE));

        if (start > end)
            throw new IllegalArgumentException(
                    String.format("start has to be <= end (start: %d, end: %d)", start, end));

        return Arrays.copyOfRange(this.data, start, end);
    }

    int[] getData()
    {
        return this.data;
    }

    int getData(int index)
    {
        if (index < 0 || index >= DATA_SIZE)
            throw new DataAccessException(
                    String.format("Memory index OOB: %d, [0, %d)", index, DATA_SIZE));

        return this.data[index];
    }

    void setData(int index, int value)
    {
        if (index < 0 || index >= DATA_SIZE)
            throw new DataAccessException(
                    String.format("Memory index OOB: %d, [0, %d)", index, DATA_SIZE));

        this.data[index] = value;
    }

    void clearData()
    {
        this.data = new int[DATA_SIZE];
    }
}
