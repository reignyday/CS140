package assignment03;

import java.util.List;
import java.util.ArrayList;

public class ClassRoomTester1
{
	private static void printList(List<Student> students)
	{
		String printed = "[";

		for (var student : students)
		{
			printed += student.getId() + ":" + student.getName() + ", ";
		}

		printed = printed.substring(0, printed.length() - 2);

		printed += "]";

		System.out.println(printed);
	}

	private static void printClassRoom(ClassRoom cr)
	{
		printList(cr.getRoster());
	}

	public static void main(String[] args)
	{
		var gina = new Student(1, "Gina");
		var tyler = new Student(2, "Tyler");
		var arnold = new Student(3, "Arnold");

		ClassRoom classRoom1 = new ClassRoom();

		classRoom1.addStudent(gina);
		classRoom1.addStudent(tyler);
		classRoom1.addStudent(arnold);
		classRoom1.addStudent(new Student(4, "Jessica"));
		classRoom1.addStudent(new Student(5, "Richard"));

		ClassRoom classRoom2 = new ClassRoom();

		classRoom2.addStudent(gina);
		classRoom2.addStudent(tyler);
		classRoom2.addStudent(arnold);
		classRoom2.addStudent(new Student(6, "Tom"));
		classRoom2.addStudent(new Student(7, "Steve"));

		ClassRoom classRoom3 = new ClassRoom();

		classRoom3.addStudent(gina);
		classRoom3.addStudent(tyler);
		classRoom3.addStudent(new Student(8, "Bob"));
		classRoom3.addStudent(new Student(9, "Stacey"));
		classRoom3.addStudent(new Student(10, "Timmy"));

		ClassRoom classRoom4 = new ClassRoom();

		classRoom4.addStudent(gina);
		classRoom4.addStudent(tyler);
		classRoom4.addStudent(new Student(12, "Jake"));
		classRoom4.addStudent(new Student(13, "Jim"));
		classRoom4.addStudent(new Student(11, "Kevin"));

		System.out.println("classRoom1:");
		printClassRoom(classRoom1);

		System.out.println("\nclassRoom2:");
		printClassRoom(classRoom2);

		System.out.println("\nclassRoom3:");
		printClassRoom(classRoom3);

		System.out.println("\nclassRoom4:");
		printClassRoom(classRoom4);

		System.out.println("\n----- q1: ClassRoom::sortById -----");
		System.out.println("ClassRoom.sortById(classRoom4.getRoster())");
		ClassRoom.sortById(classRoom4.getRoster());
		printClassRoom(classRoom4);

		System.out.println("\n----- q2: ClassRoom.isRegistered -----");
		System.out.println("classRoom1.isRegistered(7) -> expected false");
		System.out.println(classRoom1.isRegistered(7));
		System.out.println("classRoom1.isRegistered(3) -> expected true");
		System.out.println(classRoom1.isRegistered(3));

		System.out.println("\n----- q3: ClassRoom.alsoRegisteredIn -----");
		// i usually stick to 100 characters per line but screw it
		System.out.println("classRoom1.alsoRegisteredIn(classRoom2, classRoom3, classRoom4) -> expected [1:Gina, 2:Tyler]");
		printList(classRoom1.alsoRegisteredIn(classRoom2, classRoom3, classRoom4));
		System.out.println("classRoom1.alsoRegisteredIn(classRoom2) -> expected [1:Gina, 2:Tyler, 3:Arnold]");
		printList(classRoom1.alsoRegisteredIn(classRoom2));

		System.out.println("\n----- q4: ClassRoom::registeredInAll -----");
		System.out.println("ClassRoom.registeredInAll(classRoom1, classRoom2, classRoom3, classRoom4) -> expected [1:Gina, 2:Tyler]");
		printList(ClassRoom.registeredInAll(classRoom1, classRoom2, classRoom3, classRoom4));
		System.out.println("ClassRoom.registeredInAll(classRoom1, classRoom2) -> expected [1:Gina, 2:Tyler, 3:Arnold]");
		printList(ClassRoom.registeredInAll(classRoom1, classRoom2));

		System.out.println("\n----- q5: ClassRoom::registeredInOne -----");
		// 148 characters! no Fs to be found here!
		System.out.println("ClassRoom.registeredInOne(classRoom1, classRoom2, classRoom3, classRoom4) -> expected all students with no duplicates");
		printList(ClassRoom.registeredInOne(classRoom1, classRoom2, classRoom3, classRoom4));
	}
}
