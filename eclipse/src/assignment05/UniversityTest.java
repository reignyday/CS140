package assignment05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class UniversityTest
{

    @Test
    void testMakeComparator()
    {
        var u1 = new University("BU");
        var u2 = new University("MIT");
        
        var c = u1.makeComparator();
        
        for (int i = 0; i < 10; ++i)
            u1.addStudents(new Person("", "", LocalDate.now()));
        
        for (int i = 0; i < 5; ++i)
            u2.addStudents(new Person("", "", LocalDate.now()));
        
        assertEquals(true, c.compare(u1, u2) < 0);
        
        for (int i = 0; i < 5; ++i)
            u2.addStudents(new Person("", "", LocalDate.now()));
        
        assertEquals(true, c.compare(u1,  u2) < 0);
    }

}
