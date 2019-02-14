package lab04;

import java.util.Arrays;

public class FileExtractorTester
{
	public static void main(String[] args)
	{
		if (args.length != 1)
			throw new IllegalArgumentException("The program requries exactly 1 argument, the name of a file to be opened");

		var extractor = new FileExtractor(args[0]);

		System.out.println(extractor);

		extractor.removeOddElements();
		extractor.removeEvenElements();
		
		System.out.println("expect: [20, 8, 46]");
		System.out.println(extractor);

		var array = new int[5];

		System.out.println(Arrays.toString(array));

		int i = 0;

		for (var n : array)
			n = i++;

		System.out.println(Arrays.toString(array));

		String[] stringArray = {
			"string1",
			"string2",
			"string3",
			"string4" };

		for (var s : stringArray)
			System.out.println(s + " " +  s.length());

		var test1 = FileExtractor.makeUsingStream(args[0]);
		test1.streamRemoveOddElements();
		System.out.println(test1);

		var test2 = FileExtractor.oldWayButWorksOnAllFiles(args[0]);
		test2.streamRemoveOddElements();
		System.out.println(test2);
	}
}
