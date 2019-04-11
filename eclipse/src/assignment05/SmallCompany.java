package assignment05;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SmallCompany implements Employer
{
    private String companyName;
    private List<Employee> employees = new LinkedList<>();
    
    public SmallCompany(String companyName)
    {
    	this.companyName = companyName;
    }
    
    @Override
    public String toString()
    {
    	return "Company: " + this.companyName;
    }
    
    @Override
    public List<Employee> listEmployees()
    {
    	return this.employees;
    }
    
    @Override
    public List<Employee> listEmployeesSorted()
    {
    	return this.employees;
    }
    
    public void addEmp(Employee e)
    {
    	e.setEmployer(this);
    	
    	int k = Collections.binarySearch(this.employees, e);
    	
    	if (k < 0)
    		this.employees.add(-k - 1, e);
    }
    
    public void addPerson(Person p, double salary)
    {
    	this.addEmp(new Employee(p, salary));
    }
}
