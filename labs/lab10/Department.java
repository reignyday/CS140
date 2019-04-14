package lab10;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<Employee> employees;
    
    public Department(String n){
        departmentName = n;
        employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee e){
        employees.add(e);
    }
    
    public String getDepartmentName(){
        return departmentName;
    }
    
    public List<Employee> getEmployees(){
        return employees;
    }
    
    public String toString(){
        return employees.toString();
    }
}
