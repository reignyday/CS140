package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class University implements Comparable<University>
{
    private String name;
    private List<Person> faculty;
    private List<Person> staff;
    private List<Person> students;
    
    public University(String name)
    {
    	this.name = name;
    	
    	this.faculty = new ArrayList<>();
    	this.staff = new ArrayList<>();
    	this.students = new ArrayList<>();
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public boolean addFaculty(Person p)
    {
    	return this.faculty.add(p);
    }
    
    public boolean removeFaculty(Person p)
    {
    	return this.faculty.add(p);
    }
    
    public boolean addStaff(Person p)
    {
    	return this.staff.add(p);
    }
    
    public boolean removeStaff(Person p)
    {
    	return this.staff.add(p);
    }
    
    public boolean addStudents(Person p)
    {
    	return this.students.add(p);
    }
    
    public boolean removeStudents(Person p)
    {
    	return this.students.add(p);
    }
    
    @Override
    public int compareTo(University o)
    {
    	return this.students.size() - o.students.size();
    }
    
    // shouldn't this be a static method?
    public Comparator<University> makeComparator()
    {
    	Comparator<University> nameComp = (u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName());
    	
    	Comparator<University> natural = Comparator.naturalOrder();
    	Comparator<University> descending = natural.reversed();
    	
    	return descending.thenComparing(nameComp);
    }
}
