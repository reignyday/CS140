package project;

import java.util.Map;
import java.util.Set;

public class Instruction
{
    public static final Map<Integer, String> MNEMONICS = Map.ofEntries(Map.entry(0, "NOP"),
            Map.entry(1, "NOT"), Map.entry(2, "HALT"), Map.entry(3, "JUMP"), Map.entry(4, "JMPZ"),
            Map.entry(5, "LOD"), Map.entry(6, "STO"), Map.entry(7, "AND"), Map.entry(8, "CMPL"),
            Map.entry(9, "CMPZ"), Map.entry(10, "ADD"), Map.entry(11, "SUB"), Map.entry(12, "MUL"),
            Map.entry(13, "DIV"));

    public static final Map<String, Integer> OPCODES = Map.ofEntries(Map.entry("NOP", 0),
            Map.entry("NOT", 1), Map.entry("HALT", 2), Map.entry("JUMP", 3), Map.entry("JMPZ", 4),
            Map.entry("LOD", 5), Map.entry("STO", 6), Map.entry("AND", 7), Map.entry("CMPL", 8),
            Map.entry("CMPZ", 9), Map.entry("ADD", 10), Map.entry("SUB", 11), Map.entry("MUL", 12),
            Map.entry("DIV", 13)

    );

    public static final Set<String> NO_ARG_MNEMONICS = Set.of("NOP", "NOT", "HALT");
    public static final Set<String> IMM_MNEMONICS = Set.of("LOD", "ADD", "SUB", "MUL", "DIV", "AND",
            "JUMP", "JMPZ");
    public static final Set<String> IND_MNEMONICS = Set.of("LOD", "ADD", "SUB", "MUL", "DIV",
            "JUMP", "JMPZ", "STO");
    public static final Set<String> JMP_MNEMONICS = Set.of("JUMP", "JMPZ");

    byte opcode;
    int arg;

    public Instruction(byte opcode, int arg)
    {
        this.opcode = opcode;
        this.arg = arg;
    }

    public static boolean noArgument(Instruction instr)
    {
        return instr.opcode < 24;
    }

    static int numOnes(int k)
    {
        var bin = Integer.toUnsignedString(k, 2);

        return (int)bin.chars().filter(c -> c == '1').count();
    }

    static void checkParity(Instruction instr)
    {
        var num = Instruction.numOnes(instr.opcode);

        if (num % 2 == 1)
            throw new ParityCheckException("This instruction is corrupted");
    }

    public String getText()
    {
        StringBuilder build = new StringBuilder();

        build.append(Instruction.MNEMONICS.get(this.opcode / 8));
        build.append("  ");

        byte flags = (byte)((this.opcode & 0b110) >> 1);

        if (flags == 0b01)
            build.append('M');
        else if (flags == 0b10)
            build.append('N');
        else if (flags == 0b11)
            build.append('J');

        build.append(Integer.toString(this.arg, 16));

        return build.toString().toUpperCase();
    }

    public String getBinHex()
    {
        StringBuilder build = new StringBuilder();

        String s = "00000000" + Integer.toString(this.opcode, 2);
        build.append(s.substring(s.length() - 8));
        build.append("  ");
        build.append(Integer.toHexString(this.arg));

        return build.toString().toUpperCase();
    }
}
