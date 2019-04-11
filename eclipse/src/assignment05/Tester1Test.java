package assignment05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class Tester1Test
{
    @Test
    void testMakeComparator()
    {
    	var p1 = new Person("Johnny", "Smith", LocalDate.of(1971, 8, 11));
    	var p2 = new Person("Steve", "Smith", LocalDate.of(1970, 9, 14));
    	
    	var s1 = 200_000;
    	var s2 = 100_000;
    	
    	var e1 = new Employee(p1, s1);
    	var e2 = new Employee(p2, s2);
    	
    	var employer1 = new SmallCompany("memes r dreams");
    	var employer2 = new SmallCompany("abcd");
    	
    	e1.setEmployer(employer1);
    	e2.setEmployer(employer2);
    	
    	// employer asc, salary dec, person asc
    	var c = Tester1.makeComparator();
    	
    	assertEquals(true, c.compare(e1, e2) > 0);
    	
    	e1 = new Employee(p2, s2);
    	e2 = new Employee(p1, s1);
    	
    	e1.setEmployer(employer1);
    	e2.setEmployer(employer2);
    	
    	assertEquals(true, c.compare(e1, e2) > 0);
    	
    	e2.setEmployer(employer1);
    	
    	assertEquals(true, c.compare(e1, e2) > 0);
    	
    	e2.setSalary(s1);
    	
    	assertEquals(true, c.compare(e1, e2) > 0);
    	
    	var p1_new = new Person("Steve", "Smith", LocalDate.of(1970, 9, 15));
    	
    	e2 = new Employee(p1_new, s1);
    	
    	e2.setEmployer(employer1);
    	
    	assertEquals(true, c.compare(e1, e2) > 0);
    }

}
