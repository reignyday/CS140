package assignment08;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.*;
import java.util.function.Function;

public class Bonus
{
    private static List<Department> departments = new ArrayList<>();

    static {
        Employee john = new Employee("John Doe", 30);
        Employee jane = new Employee("Jane Deer", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        Employee jared = new Employee("Jared Miller", 60);
        Employee jeff = new Employee("Jeff Jackson", 27);
        Employee gerald = new Employee("Gerald House", 15);
        Employee mary = new Employee("Mary Lou", 23);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);
        accounting.addEmployee(jared);
        accounting.addEmployee(jeff);
        accounting.addEmployee(gerald);
        accounting.addEmployee(mary);

        departments.add(hr);
        departments.add(accounting);
    }

    public static void printYoungestEmployee()
    {
        Bonus.departments.stream()
                .peek(d -> System.out.println(d.getDepartmentName() + ": " + d))
                .flatMap(d -> d.getEmployees().stream())
                .reduce((a,b) -> b.getAge() < a.getAge() ? b : a)
                .ifPresent(e -> System.out.println("youngest: " + e));
    }

    public static Map<Employee, Department> listAll()
    {
        return departments.stream()
                   .flatMap(d -> d.getEmployees().stream())
                   .collect(Collectors.toMap(Function.identity(), Employee::getDepartment));
    }

    public static void main(String[] args)
    {
        printYoungestEmployee();
    }
}
