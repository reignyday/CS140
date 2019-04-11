package assignment05;

import java.util.ArrayList;
import java.util.List;

public class Company implements Employer
{
    private String companyName;
    private List<Employee> employees = new ArrayList<>();
    
    public Company(String companyName)
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
    
    public void addEmp(Employee e)
    {
        e.setEmployer(this);
        
        this.employees.add(e);
    }
    
    public void addPerson(Person p, double salary)
    {
        this.addEmp(new Employee(p, salary));
    }
}
