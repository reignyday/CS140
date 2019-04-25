package exam02;

import java.util.Arrays;

public class Parent
{
	private String name;
	private String[] children;
	
	public Parent(String n, String[] c)
	{
        if (n == null || c == null)
            throw new IllegalArgumentException("name and children cannot be null");

		this.name = n;
		this.children = c;
	}
	
	public int familySize()
	{
		return 1 + this.children.length;
	}
	
	@Override
	public String toString()
	{
		return "Parent: " + this.name + "\nChildren: " + Arrays.toString(this.children);
	}
}
