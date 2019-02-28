package exam01;

public class Person
{
	private String firstName;
	private String lastName;

	public Person(String first, String last)
	{
		this.firstName = first;
		this.lastName = last;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public String toString()
	{
		return this.firstName + " " + this.lastName;
	}
}
