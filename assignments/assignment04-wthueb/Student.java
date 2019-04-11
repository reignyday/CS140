package assignment04;

public class Student implements Comparable<Student>
{
    private int id;
    private String name;

    //Value constructor used to create a Student object
    public Student(int i, String n){
    	this.id = i;
    	this.name = n;
    }

    public int compareTo(Student stu)
    {
        return this.id - stu.id;
    }

    @Override
    public int hashCode()
    {
        return this.id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Student)
            return this.id == ((Student)obj).id;

        return false;
    }

    //returns id of the student
    public int getId(){
    	return this.id;
    }

    //returns name of the student
    public String getName(){
    	return this.name;
    }

    //printing a Student object, print's their name
    public String toString(){
    	return this.id + " " + this.name;
    }
}
