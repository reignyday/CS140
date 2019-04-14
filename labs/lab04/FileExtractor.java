package lab04;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExtractor
{
    private int[] array;
    private List<Integer> list;

    public static FileExtractor makeUsingStream(String fileName)
    {
        var ret = new FileExtractor();

        try (Stream<String> lines = Files.lines(Paths.get(fileName)))
        {
            ret.list = lines
                .map(line -> line.trim())
                .filter(line -> line.length() > 0)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return ret;
    }

    public static FileExtractor oldWayButWorksOnAllFiles(String fileName)
    {
        var ret = new FileExtractor();

        ret.list = new ArrayList<>();

        try (var b = new BufferedReader(new FileReader(fileName)))
        {
            String readLine = "";

            while ((readLine = b.readLine()) != null)
                ret.list.add(Integer.valueOf(readLine.trim()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return ret;
    }

    public FileExtractor() {}
    
    public FileExtractor(String fileName)
    {
        list = new ArrayList<Integer>();

        try (Scanner scan = new Scanner(new File(fileName)))
        {
            while (scan.hasNext())
                list.add(scan.nextInt());

            array = new int[list.size()];

            for (int i = 0; i < array.length; ++i)
                array[i] = list.get(i);
        }
        catch (FileNotFoundException e)
        {
            array = new int[0];

            System.out.println("File '" + fileName + "' not found, initializing both 'list' and 'array' to be empty\n");
        }
        catch (Exception e)
        {
            System.out.println("Error occurred while extracting data:\n");
            e.printStackTrace();
        }
    }

    public void removeOddElements()
    {
        int length = 0;

        for (var n : array)
            if (n % 2 == 0)
                ++length;

        var temp = new int[length];

        int index = 0;

        for (var n : array)
            if (n % 2 == 0)
                temp[index++] = n;

        array = temp;
    }

    public void streamRemoveOddElements()
    {
        array = list.stream()
            .mapToInt(v -> v)
            .filter(x -> x % 2 == 0)
            .toArray();
    }

    public void removeEvenElements()
    {
        var iter = list.iterator();

        while (iter.hasNext())
        {
            var n = iter.next();
            
            if (n % 2 == 0)
                iter.remove();
        }
    }

    public int[] getArray()
    {
        return array;
    }

    public List<Integer> getList()
    {
        return list;
    }

    public String toString()
    {
        return "array: " + Arrays.toString(array) + "\nlist: " + list.toString();
    }
}
