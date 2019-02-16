package assignment04;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class ClassRoom
{
	private Set<Student> roster = new TreeSet<>();

    private String className;

    public ClassRoom(String className)
    {
        this.className = className;
    }

	public static List<Student> registeredInOne(ClassRoom... others)
	{
		if (others == null || others.length == 0)
			return new ArrayList<>();

        var temp = new TreeSet<Student>();

		for (var cr : others)
            temp.addAll(cr.roster);

		return new ArrayList<>(temp);
	}

	public List<Student> alsoRegisteredIn(ClassRoom... others)
	{
		if (others == null || others.length == 0)
			return new ArrayList<>(this.roster);

		var temp = new TreeSet<Student>(this.roster);

        for (var cr : others)
            temp.retainAll(cr.roster);

        return new ArrayList<>(temp);
	}

	public void addStudent(Student s)
	{
		roster.add(s);
	}

	public void dropStudent(int id)
	{
        var iter = roster.iterator();

        while (iter.hasNext())
            if (iter.next().getId() == id)
                iter.remove();
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
		return new ArrayList<>(this.roster);
	}

    public String getClassName()
    {
        return this.className;
    }

	public String toString()
	{
        var sb = new StringBuilder(this.className);

        for (var s : roster)
            sb.append("\n" + s);

        return sb.toString();
	}
}
