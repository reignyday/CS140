package project;

import java.util.function.Consumer;
import java.util.Map;
import java.util.TreeMap;

import static project.Instruction.*;

public class Machine
{
    private class CPU
    {
        private int accum;
        private int pc;
    }

    public final Map<Integer, Consumer<Instruction>> ACTION = new TreeMap<>();

    private CPU cpu = new CPU();
    private Memory memory = new Memory();

    private boolean withGUI = false;
    private HaltCallback callBack;

    public Machine(HaltCallback cb)
    {
        this.callBack = cb;

        ACTION.put(OPCODES.get("NOP"), instr -> {
            // right shift removes parity bit, bitwise-and takes the last two bits (flags)
            // 0b01111100 >> 1 = 0b0111110, 0b0111110 & 0b11 = 0b10
            byte flags = (byte)((instr.opcode >> 1) & 0b11);
            // flags is now 0b000000??

            if (flags != 0)
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("HALT"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags != 0)
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            //this.cpu.pc++;

            this.halt();
        });

        ACTION.put(OPCODES.get("JUMP"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags == 0)
                this.cpu.pc += instr.arg;
            else if (flags == 0b01)
                this.cpu.pc = instr.arg;
            else if (flags == 0b10)
                this.cpu.pc += this.getData(instr.arg);
            else
                this.cpu.pc = this.getData(instr.arg);
        });

        ACTION.put(OPCODES.get("JMPZ"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (this.cpu.accum == 0)
            {
                if (flags == 0)
                    this.cpu.pc += instr.arg;
                else if (flags == 0b01)
                    this.cpu.pc = instr.arg;
                else if (flags == 0b10)
                    this.cpu.pc += this.getData(instr.arg);
                else
                    this.cpu.pc = this.getData(instr.arg);
            }
            else
                this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("LOD"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags == 0)
                this.cpu.accum = this.getData(instr.arg);
            else if (flags == 0b01)
                this.cpu.accum = instr.arg;
            else if (flags == 0b10)
                this.cpu.accum = this.getData(this.getData(instr.arg));
            else
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("STO"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags == 0)
                this.setData(instr.arg, this.cpu.accum);
            else if (flags == 0b10)
                this.setData(this.getData(instr.arg), this.cpu.accum);
            else
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("NOT"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags != 0)
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.accum = this.cpu.accum == 0 ? 1 : 0;

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("AND"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags == 0)
                this.cpu.accum = (this.cpu.accum != 0 && this.getData(instr.arg) != 0) ? 1 : 0;
            else if (flags == 0b01)
                this.cpu.accum = (this.cpu.accum != 0 && instr.arg != 0) ? 1 : 0;
            else
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("CMPL"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags != 0)
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.accum = this.getData(instr.arg) < 0 ? 1 : 0;

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("CMPZ"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags != 0)
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.accum = this.getData(instr.arg) == 0 ? 1 : 0;

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("ADD"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags == 0)
                cpu.accum += this.getData(instr.arg);
            else if (flags == 0b01)
                this.cpu.accum += instr.arg;
            else if (flags == 0b10)
                this.cpu.accum += this.getData(this.getData(instr.arg));
            else
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("SUB"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags == 0)
                this.cpu.accum -= this.getData(instr.arg);
            else if (flags == 0b01)
                this.cpu.accum -= instr.arg;
            else if (flags == 0b10)
                this.cpu.accum -= this.getData(this.getData(instr.arg));
            else
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("MUL"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            if (flags == 0)
                this.cpu.accum *= this.getData(instr.arg);
            else if (flags == 0b01)
                this.cpu.accum *= instr.arg;
            else if (flags == 0b10)
                this.cpu.accum *= this.getData(this.getData(instr.arg));
            else
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            this.cpu.pc++;
        });

        ACTION.put(OPCODES.get("DIV"), instr -> {
            byte flags = (byte)((instr.opcode >> 1) & 0b11);

            int divisor;

            if (flags == 0)
                divisor = this.getData(instr.arg);
            else if (flags == 0b01)
                divisor = instr.arg;
            else if (flags == 0b10)
                divisor = this.getData(this.getData(instr.arg));
            else
            {
                var fstr = "(" + Integer.toBinaryString(flags) + ")";

                throw new IllegalInstructionException(
                        "Illegal flags for this instruction:" + fstr);
            }

            if (divisor == 0)
                throw new DivideByZeroException("Cannot divide by zero.");

            this.cpu.accum /= divisor;

            this.cpu.pc++;
        });
    }

    public void halt()
    {
        this.callBack.halt();
    }

    public int[] getData(int i, int j)
    {
        return this.memory.getData(i, j);
    }

    public int getData(int i)
    {
        return this.memory.getData(i);
    }

    public int[] getData()
    {
        return this.memory.getData();
    }

    public void setData(int i, int j)
    {
        this.memory.setData(i, j);
    }

    public int getAccum()
    {
        return this.cpu.accum;
    }

    public void setAccum(int i)
    {
        this.cpu.accum = i;
    }

    public int getPC()
    {
        return this.cpu.pc;
    }

    public void setPC(int i)
    {
        this.cpu.pc = i;
    }
}
