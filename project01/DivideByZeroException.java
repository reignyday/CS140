package project;

@SuppressWarnings("serial")
public class DivideByZeroException extends RuntimeException
{
    public DivideByZeroException(String msg)
    {
        super(msg);
    }
}
