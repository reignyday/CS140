package exam02;

import java.util.List;
import java.util.ArrayList;

public class Consumer{
	
	//TODO: consumes from each supplier num times, and adds the consumed elements into a list that is returned
	
	//Note suppliers is an array
	//suppliers[0] is the first Supplier, and suppliers[suppliers.length - 1] is the last Supplier
	
	//For example, if num = 5 and suppliers = {N, O, F}
	//where N supplies negative numbers, O always supplies a one, and F supplies the Fibonacci numbers
	//consume should return [-1,-2,-3,-4,-5,   1,1,1,1,1,   1,2,3,5,8] 
	
	//since the first five negative numbers are -1,-2,-3,-4,-5
	//a one supplied five times would be 1,1,1,1,1
	//and the first five fibonacci numbers are 1,2,3,5,8
	
	//be sure the suppliers are consumed mimicking the order shown in the above example
	public static List<Integer> consume(int num, Supplier... suppliers){
		List<Integer> retVal = new ArrayList<>();
		
		for (var s : suppliers)
		{
			for (int i = 0; i < num; ++i)
				retVal.add(s.supplyNext());
		}

		return retVal;
	}
}
