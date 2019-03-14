package assignment05;

public class Employee implements Comparable<Employee>
{
	private Person person;
	private double salary;
	private Employer employer;
	
	public Employee(Person person, double salary)
	{
		this.person = person;
		this.salary = salary;
	}
	
	public Person getPerson()
	{
		return this.person;
	}
	
	public double getSalary()
	{
		return this.salary;
	}
	
	public Employer getEmployer()
	{
		return this.employer;
	}
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
	public void setEmployer(Employer employer)
	{
		this.employer = employer;
	}
	
	public double increase(double pct)
	{
		this.salary *= 1.0 + pct;
		
		return this.salary;
	}
	
	@Override
	public int compareTo(Employee o)
	{
		return this.person.compareTo(o.person);
	}
	
	@Override
	public String toString()
	{
		return this.person + String.format(" %s: $%,.2f", this.employer, this.salary);
	}
}
