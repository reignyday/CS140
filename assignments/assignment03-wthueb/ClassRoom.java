package assignment03;

import java.util.List;
import java.util.ArrayList;

public class ClassRoom
{
	private List<Student> roster = new ArrayList<Student>();
	
	public static void sortById(List<Student> list)
	{
		for (int i = 0; i < list.size(); ++i)
		{
			for (int j = 1; j < list.size() - i; ++j)
			{
				var high = list.get(j);
				var low = list.get(j - 1);

				if (low.getId() > high.getId())
				{
					list.set(j, low);
					list.set(j - 1, high);
				}
			}
		}
	}

	public static List<Student> registeredInAll(ClassRoom... others)
	{
		if (others == null || others.length == 0)
			return new ArrayList<Student>();

		return others[0].alsoRegisteredIn(others);
	}

	public static List<Student> registeredInOne(ClassRoom... others)
	{
		if (others == null || others.length == 0)
			return new ArrayList<Student>();

		List<Student> list = new ArrayList<>();

		for (var other : others)
		{
			var students = other.getRoster();

			for (var student : students)
			{
				// this doesn't work if a new Student object is created with the same id
				//if (!list.contains(student))
				//	list.add(student);
				
				// better to be safe and check by id. efficiency should effectively be the same
				boolean exists = false;

				for (var s : list)
				{
					if (s.getId() == student.getId())
					{
						exists = true;
						break;
					}
				}
				
				if (!exists)
					list.add(student);
			}
		}

		ClassRoom.sortById(list);

		return list;
	}

	public List<Student> alsoRegisteredIn(ClassRoom... others)
	{
		// shallow copy, but it doesn't matter because we aren't altering the Student objects
		// same as ArrayList.clone()
		List<Student> list = new ArrayList<>(this.roster);

		if (others == null || others.length == 0)
			return list;

		for (int i = list.size() - 1; i >= 0; --i)
		{
			int id = list.get(i).getId();

			for (var other : others)
			{
				if (!other.isRegistered(id))
				{
					list.remove(i);
					break;
				}
			}
		}

		return list;
	}

	public void addStudent(Student s)
	{
		roster.add(s);
	}

	public boolean dropStudent(int id)
	{
		for (int i = 0; i < roster.size(); ++i)
		{
			var s = roster.get(i);

			if (s.getId() == id)
			{
				roster.remove(i);

				return true;
			}
		}

		return false;
	}

	public void sortById()
	{
		for (int i = 0; i < roster.size(); ++i)
		{
			for (int j = 1; j < roster.size() - i; ++j)
			{
				var high = roster.get(j);
				var low = roster.get(j - 1);

				if (low.getId() > high.getId())
				{
					roster.set(j, low);
					roster.set(j - 1, high);
				}
			}
		}
	}

	public boolean isRegistered(int id)
	{
		for (var student : roster)
			if (student.getId() == id)
				return true;

		return false;
	}

	public List<Student> getRoster()
	{
		return this.roster;
	}

	public String toString()
	{
		return roster.toString();
	}
}
