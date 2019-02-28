package exam01;

//ADD IMPORTS NEEDED HERE
import java.util.List;
import java.util.ArrayList;

public class Directory{
	private List<Person> people = new ArrayList<>();

	public void addPerson(Person p)
	{
		if (p == null)
			throw new IllegalArgumentException("null person not allowed");

		this.people.add(p);
	}

	public Person latestName()
	{
		if (this.people.size() == 0)
			return null;

		var p = this.people.get(0);

		String latestFirst = p.getFirstName();
		String latestLast = p.getLastName();

		for (int i = 1; i < this.people.size(); ++i)
		{
			p = this.people.get(i);

			if (isLater(p.getFirstName(), latestFirst))
				latestFirst = p.getFirstName();

			if (isLater(p.getLastName(), latestLast))
				latestLast = p.getLastName();
		}

		return new Person(latestFirst, latestLast);
	}

	public void makeAFamily(String last)
	{
		// race conditions aren't in the scope of this class right?
		List<Person> fam = new ArrayList<>();

		for (var p : this.people)
		{
			var first = p.getFirstName();

			fam.add(new Person(first, last));
		}

		this.people = fam;
	}

	public String toString()
	{
		return this.people.toString();
	}

	//-----------------------------------------------------------------------
	//this method is provided, do not alter it!
	//returns true if str1 isLater than str2 alphabetically
	private boolean isLater(String str1, String str2){
		return str1.compareTo(str2) > 0;
	}
	//----------------------------------------------------------------------


}
