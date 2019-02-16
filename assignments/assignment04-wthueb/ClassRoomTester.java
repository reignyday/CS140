package assignment04;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class ClassRoomTester
{
    public static void saveClassRoom(ClassRoom cr)
    {
        try (var pw = new PrintWriter(cr.getClassName() + ".text"))
        {
            pw.println(cr);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static ClassRoom readClassRoom(String fileName)
    {
        ClassRoom cr = null;

        try (var input = new Scanner(new File(fileName)))
        {
            cr = new ClassRoom();

            cr.setClassName(input.nextLine().trim());
            
            while (input.hasNext())
            {
                int i = input.nextInt();
                String name = input.nextLine();

                cr.addStudent(new Student(i, name));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return cr;
    }

	public static void main(String[] args)
	{
        var students = new ArrayList<Student>();

        for (int i = 0; i < 100; ++i)
            students.add(Resources.getStudent());

        String[] names = {
            "History",
            "Computer Science",
            "Math",
            "English",
            "Gender Studies"
        };

        for (int i = 0; i < names.length; ++i)
        {
            ClassRoom cr = new ClassRoom();

            cr.setClassName(names[i]);

            for (int j = 0; j < students.size(); j += 2+i)
                cr.addStudent(students.get(j));

            saveClassRoom(cr);
        }
	}
}
