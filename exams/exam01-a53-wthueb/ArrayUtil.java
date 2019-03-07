package exam01;

import java.util.Arrays;

public class ArrayUtil{

	//counts how many times the minimum of each array occurs, and returns these counts in an array
	public static int[] countMins(int[]... arrays){
		if (arrays == null)
			throw new IllegalArgumentException("arrays was null.");

		if (arrays.length == 0)
			return new int[0];

		// there are so many better ways to do this but i'll follow the directions!
		int[] mins = new int[arrays.length];

		for (int i = 0; i < arrays.length; ++i)
		{
			int min = arrays[i][0];

			for (int j = 1; j < arrays[i].length; ++j)
			{
				if (arrays[i][j] < min)
					min = arrays[i][j];
			}

			mins[i] = min;
		}

		int[] minCounts = new int[mins.length];

		for (int i = 0; i < arrays.length; ++i)
		{
			int count = 0;

			for (int j = 0; j < arrays[i].length; ++j)
				if (arrays[i][j] == mins[i])
					++count;

			minCounts[i] = count;
		}

		return minCounts;
	}
}
