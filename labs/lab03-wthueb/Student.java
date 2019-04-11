package lab03;

public class Student{
    private int id;
    private String name;

    //Value constructor used to create a Student object
    public Student(int i, String n){
    	this.id = i;
    	this.name = n;
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
    	return this.name;
    }
}
