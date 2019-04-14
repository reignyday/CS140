package assignment07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A tree in which each node has an arbitrary number of children. 
 * The tree stores data that is Comparable. The nested Node time
 * is also Comparable based in the compareTo method if the data in the Nodes
 */
public class Tree <T extends Comparable<T>> { 
    private Node root;
    /**
     * Define the type Node as an inner class. The children list is never null:
     * it is instantiated as an empty list in the declaration.
     * The constructor inserts a value for the data in the Node. Node is
     * made Comparable using data.compareTo(other.data).
     */
    class Node implements Comparable<Node>{
        public T data;
        public List<Node> children = new ArrayList<>(); 
        Node(T nodeData) {
            data = nodeData;
        }
        
        /**
         * Computes the size of the subtree whose root is this node.
         * @return the number of nodes in the subtree
         */
        public int size() {
            int sum = 0;
            for (Node child : children) {
                sum = sum + child.size(); 
            }
            return 1 + sum;
        }
        /**
         * This find method returns the Node that has "element" as
         * its data and null if none is found
         * @param element the data we are looking for
         * @return the node with element as its data or null if there is none.
         */
        public Node find(T element) {
            // If element is null, return null (return null even
            // if data is null). if element equals data, then return this Node
            // -- just write: return this;
            // The next case (element is not equal to data) then loop through the 
            // children and if a recursive call on one of children is not null, 
            // return that element. If no node is found in this recursion, return null.
            
            if (element == null)
                return null;

            if (element == this.data)
                return this;

            for (var child : this.children)
            {
                var found = child.find(element);

                if (found != null)
                    return found;
            }

            return null;
        }
        /**
         * Add element as the data of Node in the children of this node.
         * @param element the element to be inserted as a child of this Node
         * @return true if the element is inserted, otherwise false.
         */
        public boolean addChild(T element) {
            // if element is false return false.
            // first call find(element). If that is not null, then element
            // is already stored, so return false.
            // Otherwise make a new Node with element as its data field and
            // add this node to children. Call Collections.sort(children);
            // return true.     
            
            // what is the purpose of this
            if (element == null)
                return false;

            if (this.find(element) != null)
                return false;

            this.children.add(new Node(element));

            Collections.sort(this.children);

            return true;
        }

        @Override
        public int compareTo(Tree<T>.Node o) {
            if(o == null) return 1;
            return data.compareTo(o.data);
        }
    }
    /**
     * Find the Node which has element as data in this tree.
     * @param element the data we are looking for
     * @return the Node in this Tree with element as its data or null if there is none.
     */
    public Node find(T element) {
        return root.find(element);
    }
    /**
     * Inserts a new Node with element as its data into the children
     * of the Node containing the given rootData. Return true if successful.
     * Returns false if the element is already in the tree.
     * @param element the data to be inserted as a new Node into this Tree
     * @param rootData the data field of the Node which will have the new Node as a child
     * @return true if the insertion is successful, otherwise false.
     */
    public boolean insertAsAChild(T element, T rootData) {
        // if element or rootData is null return false.
        // if find(element) is not null, return false, since element is in this Tree
        // Find the Node n such that n.data equals rootData.
        // If n is not null, call n.addChild(element);
        // and return true
        
        if (element == null || rootData == null)
            return false;

        if (this.find(element) != null)
            return false;

        var n = this.find(rootData);

        if (n == null)
            return false;

        return n.addChild(element);
    }

    /**
     * Print the tree to the console
     */
    public void print() {
        print(root, 0);
    }
    /**
     * Helper method to print the Tree elements with indentation 
     * @param n Node that is currently being printed
     * @param indent amount of indentation
     */
    public void print(Node n, int indent) {
        // First print indent copies of "  ", i.e. 2 spaces
        // Print "-"
        // Print n.data followed by a new line
        // recursively print all the children with (indent+1)
        
        for (int i = 0; i < indent; ++i)
            System.out.print("  ");

        System.out.println("-" + n.data);

        for (var child : n.children)
            print(child, indent + 1);
    }

// NEXT A BUNCH OF CODE PROVIDED IN THE TEXTBOOK
    /**
     * Constructs an empty tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * Constructs a tree with one node and no children.
     * @param rootData the data for the root
     */
    public Tree(T rootData) {
        root = new Node(rootData);
    }

    /**
     * Adds a subtree as the last child of the root.
     */
    public void addSubtree(Tree<T> subtree) {
        root.children.add(subtree.root);
        Collections.sort(root.children);
    }

    /**
     * Computes the size of this tree.
     * @return the number of nodes in the tree
     */
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size(); 
        }
    }

    /**
     * A visitor whose visit method is called for each visited node
     * during a tree traversal.
     */
    public interface Visitor {
        /**
         * This method is called for each visited node.
         * @param data the data of the node
         */
        void visit(Object data);
    }

    /**
     * Traverses this tree in preorder.
     * @param v the visitor to be invoked at each node
     */
    public void preorder(Visitor v) { 
        preorder(root, v); 
    }

    /**
     * Traverses the tree with a given root in preorder.
     * @param n the root of the tree
     * @param v the visitor to be invoked at each node
     */
    private void preorder(Node n, Visitor v) {
        if (n == null) { return; }
        v.visit(n.data);
        for (Node c : n.children) { preorder(c, v); }
    }   

    public static void main(String[] args) {
        var dirTree = new Tree<String>("C:\\");
        dirTree.insertAsAChild("Program Files", "C:\\");
        dirTree.insertAsAChild("Windows", "C:\\");
        dirTree.insertAsAChild("Users", "C:\\");
        dirTree.insertAsAChild("Java","Program Files");
        dirTree.insertAsAChild("Git","Program Files");
        dirTree.insertAsAChild("Windows Defender","Program Files");
        dirTree.insertAsAChild("Lander","Users");
        dirTree.insertAsAChild("eclipse-workspace","Lander");
        dirTree.insertAsAChild("cs140","eclipse-workspace");
        dirTree.insertAsAChild("src","cs140");
        dirTree.insertAsAChild("assignment05","src");
        dirTree.print();
    }
}
