package lab09;

import java.util.Comparator;
import java.util.List;

public class Bonus
{
    public static void main(String[] args)
    {
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

        System.out.println(tree.nonLeaves());

        // change comparator to ascending order now
        Comparator<String> strComp = (s1, s2) -> s1.length() - s2.length();
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

        System.out.println(stree.nonLeaves());
    }
}
