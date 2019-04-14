package assignment04;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class ClassRoomTester2
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
            cr = new ClassRoom(input.nextLine().trim());

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
        var classRoom1 = readClassRoom("History.text");
        var classRoom2 = readClassRoom("Computer Science.text");
        var classRoom3 = readClassRoom("Math.text");
        var classRoom4 = readClassRoom("English.text");
        var classRoom5 = readClassRoom("Gender Studies.text");

        System.out.println("----- classRoom2.alsoRegisteredIn(classRoom4, classRoom5) -----");
        System.out.println(classRoom2.alsoRegisteredIn(classRoom4, classRoom5));

        System.out.println("----- ClassRoom.registeredInOne(classRoom2, classRoom4, classRoom5) -----");
        System.out.println(ClassRoom.registeredInOne(classRoom2, classRoom4, classRoom5));
    }
}
