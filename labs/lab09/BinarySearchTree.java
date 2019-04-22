package lab09;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to implement the Binary Search Tree data structure.
 * The structure is generic in the type of Objects it contains.
 * @param <T> the type of the contained elements this structure is to hold
 */
public class BinarySearchTree<T> {
    private Comparator<T> comparator;
    private T data;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    /**
     * Constructs an empty BST with a Comparator
     * @param comp the Comparator to use to impose an ordering on instances of T
     */
    public BinarySearchTree(Comparator<T> comp) {
        this.comparator = comp;
    }

    /**
     * Constructs a BST with data and a Comparator
     * @param data the data this BST should hold
     * @param comp the Comparator to use to impose an ordering on instances of T
     */
    public BinarySearchTree(T data, Comparator<T> comp) {
        this.data = data;
        this.comparator = comp;
    }

    /**
     * Inserts an element into this BST
     * if element is null, do not insert element
     * it is worth noting that the only time data could be null is if we created a BST with no initial data
     * in that case, if data is null, we just update data to equal element, and the insertion is done
     * if the element to be inserted compares equal to another value that is already in the tree, do not insert element
     * we do this for the sake of simplicity, forcing elements in the tree to be unique
     * @param element the element to insert into this BST
     */
    public void insert(T element) {
        if (element == null)
            return;

        if (this.data == null)
        {
            this.data = element;
            return;
        }

        var comp = this.comparator.compare(element, this.data);

        if (comp == 0)
            return;

        if (comp < 0)
        {
            if (this.left == null)
            {
                this.left = new BinarySearchTree<T>(element, this.comparator);
                return;
            }

            this.left.insert(element);
        }
        else
        {
            if (this.right == null)
            {
                this.right = new BinarySearchTree<T>(element, this.comparator);
                return;
            }

            this.right.insert(element);
        }
    }

    /**
     * Searchs for a given element in this BST
     * if element is null, return null
     * @param element the element to search this BST for
     * @return the element in this BST matching the given element, if found,
     *         otherwise null if the given element is not in this BST
     */
    public T find(T element) {
        if (element == null || this.data == null)
            return null;

        int comp = this.comparator.compare(element, this.data);

        if (comp == 0)
            return this.data;

        if (comp < 0 && this.left != null)
            return this.left.find(element);
        else if (this.right != null)
            return this.right.find(element);

        return null;
    }

    /**
     * Gathers all the elements of this BST in order
     * @return a List holding the elements in this BST in order
     */
    public List<T> getElements() {
        List<T> list = new ArrayList<>();

        if (this.left != null)
            list.addAll(this.left.getElements());

        if (this.data != null)
            list.add(this.data);

        if (this.right != null)
            list.addAll(this.right.getElements());

        return list;
    }

    /**
     * Pretty prints the contents of this BST in a horizontal tree-like fashion
     */
    public void prettyPrint() {
        prettyPrint(0);
    }

    private void prettyPrint(int indentLevel) {
        // print `indentLevel` amount of spaces before printing data on its own line
    //HINT: you will want to do an in order traversal here. (see the Traversal section in the explanation of Binary Trees above)
        // you may use a for loop to print `indentLevel` amount of spaces
        // each time you recurse, you add to indentLevel
        
        if (this.data == null) // stopping condition
            return;

        if (this.right != null)
            this.right.prettyPrint(indentLevel + 4);

        for (int i = 0; i < indentLevel; ++i)
            System.out.print(" ");

        System.out.println(this.data);

        if (this.left != null)
            this.left.prettyPrint(indentLevel + 4);
    }

    public List<T> nonLeaves()
    {
        List<T> sticks = new ArrayList<>();

        if (this.left != null)
            sticks.addAll(this.left.nonLeaves());

        if (this.left != null || this.right != null)
            sticks.add(this.data);

        if (this.right != null)
            sticks.addAll(this.right.nonLeaves());

        return sticks;
    }

    /**
     * A main method supplied for any debugging needs
     */
    public static void main(String[] args) {
        // Up to you how you use this main method, feel free to change it
        Comparator<Integer> intComp = (i, j) -> i - j; // lambda expression
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(intComp);
        tree.insert(3);
        tree.insert(8);
        tree.insert(1);
        tree.insert(0);
        tree.insert(3);
        tree.insert(9);
        tree.insert(4);
        tree.prettyPrint();

        Comparator<String> strComp = (s1, s2) -> s2.length() - s1.length();
        BinarySearchTree<String> stree = new BinarySearchTree<>(strComp);
        stree.insert("penta");
        stree.insert("hi");
        stree.insert("a");
        stree.insert("oranges");
        stree.insert("cat");
        stree.insert("test");
        stree.insert("stella");
        stree.insert("oxymoron");
        stree.insert("blackjack");
        stree.prettyPrint();
    }
}
