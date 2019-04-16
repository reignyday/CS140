package assignment08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Assignment08Test
{
    @Test
    void testListAll()
    {
    	Employee jane = new Employee("Jane Deer", 25);
    	Employee john = new Employee("John Doe", 30);
        Employee jared = new Employee("Jared Miller", 60);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);
        accounting.addEmployee(jared);
        
        var depts = new ArrayList<Department>();
        depts.add(hr);
        depts.add(accounting);
        
    	var comp = new HashMap<Employee, Department>();
    	
    	comp.put(jane, hr);
    	comp.put(john, accounting);
    	comp.put(jared, accounting);
    	
    	assertEquals(comp, Bonus.listAll(depts));
    }
}
