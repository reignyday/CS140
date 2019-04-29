package exam02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Tester {
    //QUESTION 1 on Recursion
    @Test
    @DisplayName("testing alterLeaves()")
    public void alterLeavesTest() {
        System.out.println("\n-----Question 1 BST-----\n");
        
        BinarySearchTree tree = new BinarySearchTree(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(35);
        tree.insert(3);
        tree.insert(7);

        tree.prettyPrint();

        tree.alterLeaves(2);
        
        System.out.println("----------After Altering Leaves (doubled values)------------");
        tree.prettyPrint();
    }
    
    @Test
    @DisplayName("testing lcm()")
    public void lcmTest() {     
        assertEquals(84, BinarySearchTree.lcm(42,28));
    }
    
    //Question 2 on Interfaces
    @Test
    @DisplayName("testing Supplier interface()")
    public void testConsumer() {        
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(-1,-2,-3,-4,-5));
        List<Integer> actual = Consumer.consume(5, new Negatives());
        assertEquals(expected, actual);

        //TODO: Define the Supplier s with an appropriate lambda expression,
        //a supplier which always returns ones
        Supplier ones = () -> 1;

        List<Integer> expected1 = new ArrayList<Integer>(Arrays.asList(-6,-7,-8,-9,-10,    1,1,1,1,1,    1,2,3,5,8));
        List<Integer> actual1 = Consumer.consume(5, new Negatives(), ones, new Fibonacci());
        assertEquals(expected1, actual1);
        
    }
    
    //Question 3 on Subclassing
    @Test
    @DisplayName("testing subclassing")
    public void testParents() {
        System.out.println("\n-----Question 3-----\n");
        
        String[] children = {"Jessica", "David"};
        String[] grandChildren = {"James", "Abby", "Robert"};
        String name = "Wilfred";

        Parent p = new Parent(name, children);

        System.out.println(p);
        assertEquals(3, p.familySize());

        System.out.println("-------------------");

        GrandParent gp = new GrandParent(name, children, grandChildren);

        System.out.println(gp);
        assertEquals(6, gp.familySize());
    }
}


