package exam01;

import java.util.ArrayList;
import java.util.List;

public class ListUtil{

    public static List<Integer> exclusiveSum(List<Integer> list){
    	if (list == null || list.size() == 0)
    		throw new IllegalArgumentException("Bad input.");

    	List<Integer> exclusive_sums = new ArrayList<>();

    	for (int i = 0; i < list.size(); ++i)
    	{
    		int sum = 0;

    		for (int j = 0; j < list.size(); ++j)
    			if (j != i)
    				sum += list.get(j);

    		exclusive_sums.add(sum);
    	}

    	return exclusive_sums;
    }
}
