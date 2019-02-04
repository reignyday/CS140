package assignment02;

public class LoanUtility
{
	public static void putInDescendingOrder(LoanPackage[] loans)
	{
		for (int i = 0; i < loans.length - 1; ++i)
		{
			int j = indexOfFirstLargestAmountDue(i, loans);

			if (i != j)
			{
				LoanPackage.exchangeFields(loans[i], loans[j]);
			}
		}
	}

	public static int indexOfFirstLargestAmountDue(int start, LoanPackage[] loans)
	{
		var index = start;

		for (int i = start + 1; i < loans.length; ++i)
		{
			if (loans[index].compareTo(loans[i]) < 0)
			{
				index = i;
			}
		}

		return index;
	}
}
