package project;

@SuppressWarnings("serial")
public class IllegalInstructionException extends RuntimeException
{
    public IllegalInstructionException(String msg)
    {
        super(msg);
    }
}
