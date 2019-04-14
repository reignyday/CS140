package assignment08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class Assignment08Test
{
    @Test
    void testListAll()
    {
    	var comp = new HashMap<Employee, Department>();
    	
    	
    	
    	assertEquals(comp, Bonus.listAll());
    }
}
