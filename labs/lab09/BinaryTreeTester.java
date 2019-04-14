package lab09;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class BinaryTreeTester {
    private final List<Integer> data = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 0, -1, 2, 3));;
    private final Comparator<Integer> intComp = (i, j) -> i - j;
    private BinarySearchTree<Integer> ints;

    @BeforeEach
    public void setup() {
        ints = new BinarySearchTree<>(intComp);
        for (int i : data) {
            ints.insert(i);
        }
    }

    @Test
    @DisplayName("testing find method")
    public void testingFind() {
        for (int i : data) {
            assertNotNull(ints.find(i));
        }
        assertNull(ints.find(6));
        assertNull(ints.find(-3));
        assertNull(ints.find(-4));
    }

    @Test
    @DisplayName("testing getElements")
    public void testingGetElements() {
        List<Integer> elems = ints.getElements();
        List<Integer> gotten = new ArrayList<>();
        for (int i : elems) {
            assertNotNull(ints.find(i));
            assertFalse(gotten.contains(i));
            gotten.add(i);
        }
        for (int i : data) {
            assertTrue(elems.contains(i));
        }
        assertEquals(7, elems.size());
        
        for (int i = 1; i < elems.size(); i++) {
            assertTrue(elems.get(i-1) < elems.get(i));
        }
    }
}
