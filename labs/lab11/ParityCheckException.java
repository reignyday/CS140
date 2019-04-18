package project;

import java.util.IllegalFormatFlagsException;

@SuppressWarnings("serial")
public class ParityCheckException extends IllegalFormatFlagsException
{
	public ParityCheckException(String msg)
    {
        super(msg);
    }
}
