package assignment05;

import java.time.LocalDate;

public class Person implements Comparable<Person>
{
	private String firstName;
	private String lastName;
	private LocalDate dob;

	public Person(String firstName, String lastName, LocalDate dob)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}

	@Override
	public int compareTo(Person o)
	{
		int ret = this.lastName.compareToIgnoreCase(o.lastName);

		if (ret != 0)
			return ret;

		ret = this.firstName.compareToIgnoreCase(o.firstName);

		if (ret != 0)
			return ret;

		ret = this.dob.compareTo(o.dob);

		return ret;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder(this.firstName);
		
		sb.append(" ");
		sb.append(this.lastName);
		sb.append(" (");
		sb.append(dob);
		sb.append(")");
		
		return sb.toString();
	}
}
