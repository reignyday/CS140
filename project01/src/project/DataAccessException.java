package project;

@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException
{
    public DataAccessException(String msg)
    {
        super(msg);
    }
}
