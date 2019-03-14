package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Employer extends Comparable<Employer>
{
	abstract List<Employee> listEmployees();
	
	@Override
	default int compareTo(Employer o)
	{
		return this.toString().compareToIgnoreCase(o.toString());
	}
	
	default List<Employee> listEmployeesSorted()
	{
		List<Employee> temp = new ArrayList<>();
		
		temp.addAll(this.listEmployees());
		
		Collections.sort(temp);
		
		return temp;
	}
}
