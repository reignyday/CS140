package project;

public class Test
{
    public static void main(String[] args)
    {
        var instr = new Instruction((byte)9, 0);
        
        System.out.printf("parity checking: %d (%s)\n", instr.opcode, "1001");

        try
        {
            Instruction.checkParity(instr);

            System.out.println("parity check passed");
        }
        catch (ParityCheckException e)
        {
            System.out.println("parity check failed: " + e);
        }
        
        instr = new Instruction((byte)11, 0);
        
        System.out.printf("parity checking: %d (%s)\n", instr.opcode, "1011");

        try
        {
            Instruction.checkParity(instr);

            System.out.println("parity check passed");
        }
        catch (ParityCheckException e)
        {
            System.out.println("parity check failed: " + e);
        }
    }
}
