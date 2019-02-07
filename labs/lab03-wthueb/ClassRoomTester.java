package lab03;
import java.util.List;
import java.util.ArrayList;

public class ClassRoomTester{
	public static void main(String[] args){
		ClassRoom classRoom = new ClassRoom();

		classRoom.addStudent(new Student(5, "Gina"));
		classRoom.addStudent(new Student(3, "Tyler"));
		classRoom.addStudent(new Student(4, "Arnold"));
		classRoom.addStudent(new Student(1, "Jessica"));
		classRoom.addStudent(new Student(2, "Richard"));

		System.out.println("**************Original Class Roster**********");
		System.out.println(classRoom);

		classRoom.dropStudent(3);

		System.out.println("\n*******Dropped Tyler from the Roster**********");
		System.out.println(classRoom);

		classRoom.sortById();

		System.out.println("\n*******Roster after sorting by Id's**********");
		System.out.println(classRoom);

		for(Student s : classRoom.getRoster()){
			System.out.println("Name: " + s.getName() + "\tId: " + s.getId());
		}

	}
}