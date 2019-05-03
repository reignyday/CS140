package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Memory
{
    public static final int DATA_SIZE = 512;
    public static final int CODE_SIZE = 256;

    private int[] data = new int[Memory.DATA_SIZE];
    private List<Instruction> code = new ArrayList<>();
    private int changedDataIndex = -1;

    int[] getData(int start, int end)
    {
        if (start < 0 || end >= Memory.DATA_SIZE)
            throw new DataAccessException(String.format("Memory index OOB: %d-%d, [0, %d)", start,
                    end, Memory.DATA_SIZE));

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
        if (index < 0 || index >= Memory.DATA_SIZE)
            throw new DataAccessException(
                    String.format("Memory index OOB: %d, [0, %d)", index, Memory.DATA_SIZE));

        return this.data[index];
    }

    void setData(int index, int value)
    {
        if (index < 0 || index >= Memory.DATA_SIZE)
            throw new DataAccessException(
                    String.format("Memory index OOB: %d, [0, %d)", index, Memory.DATA_SIZE));

        this.data[index] = value;

        this.changedDataIndex = index;
    }

    void clearData()
    {
        this.data = new int[Memory.DATA_SIZE];

        this.changedDataIndex = -1;
    }

    List<Instruction> getCode()
    {
        return this.code;
    }

    Instruction getCode(int index)
    {
        if (index < 0 || index >= Memory.CODE_SIZE)
            throw new CodeAccessException("illegal access to code");

        return this.code.get(index);
    }

    Instruction[] getCode(int start, int end)
    {
        if (start < 0 || end >= Memory.CODE_SIZE || start > end)
            throw new CodeAccessException("illegal access to code");

        Instruction[] temp = {};

        temp = this.code.toArray(temp);

        return Arrays.copyOfRange(temp, start, end);
    }

    void addCode(Instruction value)
    {
        if (this.code.size() >= Memory.CODE_SIZE)
            return;

        this.code.add(value);
    }

    void setCode(int index, Instruction instr)
    {
        if (index < 0 || index >= Memory.CODE_SIZE)
            throw new CodeAccessException("illegal access to code");

        this.code.set(index, instr);
    }

    void clearCode()
    {
        this.code.clear();
    }

    int getProgramSize()
    {
        return this.code.size();
    }

    int getChangedDataIndex()
    {
        return this.changedDataIndex;
    }
}
