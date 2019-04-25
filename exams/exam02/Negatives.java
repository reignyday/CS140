package exam02;

public class Negatives implements Supplier
{
	private static int num = -1;
	
	@Override
	public int supplyNext()
	{
		return Negatives.num--;
	}
}
