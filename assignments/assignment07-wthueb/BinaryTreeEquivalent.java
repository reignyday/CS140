package assignment07;

import java.util.Collections;

/**
 * A tree where a list of Children nodes is converted to a special
 * kind of binary tree.
 */
public class BinaryTreeEquivalent <T extends Comparable<T>> {
	
	class BinaryLink {
		public Node left;
		public BinaryLink right;
	}
	
	class Node implements Comparable<Node> {
		// Note we are not going to use that Node is Comparable here;
		// keeping children in order with be done manually by the code
		public T data;
		public BinaryLink children;

		Node(T nodeData) {
			data = nodeData;
		}

		/**
		 * Computes the Nodes of the subtree whose root is this Node.
		 * It does NOT count the BinaryLinks in this count.
		 * @return the number of Nodes in the subtree
		 */
		public int size() {
			// instead of for (Node child : children) ...
			// you need 
			// BinaryLink child = children;
			// while(child != null) {
			//	...
			//	child = child.right;
			// }
            var child = this.children;

            int size = 0;

            while (child != null)
            {
                size += child.left.size();

                child = child.right;
            }

            return size;
		}
// There is a fancier solution by making an Iterator and the BinaryLink Iterable 
// but it is good practice to learn how to loop through lists.
// If you implement Iterable you can use the enhanced for.

		/**
		 * This find method returns the Node that has "element" as
		 * its data and null if none is found
		 * @param element the data we are looking for
		 * @return the node with element as its data or null if there is none.
		 */
		public Node find(T element) {
			// if element is null return null, if element equals data return
			// this Node, if children is null return null. Make a BinaryLink child
			// set to be children. In a while loop that moves child to child.right at the end
			// of the loop, let n be the recursive call child.left.find(element) and return
			// n if it not null.
			// if the while loop ends, return null.
            
            if (element == null)
                return null;

            if (element == this.data)
                return this;

            if (this.children == null)
                return null;

            var child = this.children;

            while (child != null)
            {
                var n = child.left.find(element);

                if (n != null)
                    return n;

                child = child.right;
            }

            return null;
		}

		/**
		 * Add element as the data of Node in the children of this node.
		 * @param element the element to be inserted as a child of this Node
		 * @return true if the element is inserted, otherwise false.
		 */
		public boolean addChild(T element) {
			// the hardest part is remembering which Node you came from
			// when you reach the end of the list (null), which is the place to make
			// the insert.

			// if element is null or find(element) is not null return false;
            if (element == null || this.find(element) != null)
                return false;

			// Make a new Node(element) and call it temp.
            var temp = new Node(element);

			// if children is null make a new BinaryLink with left equal to temp and
			// change children to this BinaryLink.
            if (children == null)
            {
                var bl = new BinaryLink();

                bl.left = temp;

                this.children = bl;
            }
			// else there is a nested if/else: call it if1 and else1
			// The if1 part checks if element.compareTo(children.left.data) <= 0
            else
            {
                // if1
                if (element.compareTo(children.left.data) <= 0)
                {
                    // make a new BinaryLink with left equal to temp, right equal to children 
                    // and change children to this BinaryLink.
                    var bl = new BinaryLink();

                    bl.left = temp;
                    bl.right = this.children;

                    this.children = bl;
                }
                // else1
                else
                {
                    // The else1 part works with a BinaryLink current set to children and
                    // a BinaryLink child set to current.right.
                    var current = this.children;
                    var child = this.children.right;

                    // We now walk these 2 pointers down the links till we find the place
                    // to make the insert:
                    // while child is not null and element.compareTo(child.left.data) > 0
                    while (child != null && element.compareTo(child.left.data) > 0)
                    {
                        // change current to child and child to current.right.
                        current = child;
                        child = current.right;
                    }
                    // The while exits when child is null or temp has to precede child.left.

                    // Another if/else, call it if2 and else2
                    // If2 is when child is null: make a new BinaryLink and set current.right 
                    // equal to it and set the left of that BinaryLink to temp.
                    if (child == null)
                    {
                        var bl = new BinaryLink();

                        bl.left = temp;
                        bl.right = current.right;
                    }
                    else
                    {
                        // Else2 inserts a new BinaryLink bl between two existing ones bl.left
                        // is temp bl.right is child and current.right is changed to bl (the
                        // correct order of the last two pointer changes is vital).			
                        var bl = new BinaryLink();

                        bl.left = temp;
                        bl.right = child;

                        current.right = bl;
                    }
                }
            }

			// At the very end of the method return true.
            return true;
		}

		@Override
		public int compareTo(BinaryTreeEquivalent<T>.Node o) {
			if(o == null) return 1;
			return data.compareTo(o.data);
		}
	}

	private Node root;

	public Node find(T element) {
		return root.find(element);
	}
	
	public boolean insertAsAChild(T element, T rootData) {
		// see the method above in Tree
        
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
	 * Constructs an empty tree.
	 */
	public BinaryTreeEquivalent() { 
		root = null; 
	} 

	/**
      Constructs a tree with one node and no children.
      @param rootData the data for the root
	 */
	public BinaryTreeEquivalent(T rootData) {
		root = new Node(rootData);
	}

	/**
	 * Checks whether this tree is empty.
	 * @return true if this tree is empty
	 */
	public boolean isEmpty() { 
		return root == null; 
	}

	/**
      Gets the data at the root of this tree.
      @return the root data
	 */
	public T data() {
		return root.data; 
	}
	public void print() {
		print(root, 0);
	}
	/**
	 * Helper method to print the Tree elements with indentation 
	 * @param n Node that is currently being printed
	 * @param indent amount of indentation
	 */
	public void print(Node n, int indent) {
		//TODO
		// First print indent copies of "  ", i.e. 2 spaces
		// Print "-"
		// Print n.data followed by a new line
		// BinaryLink child = n.children;
		// while child is not null recursively print child.left with (indent + 1)
		// and change child to child.right
	}
	// WE WILL SKIP THE OTHER METHOD THAT THE TEXTBOOK HAS FOR Tree

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
        System.out.println("--------------");
        var dirTree1 = new BinaryTreeEquivalent<String>("C:\\");
        dirTree1.insertAsAChild("Program Files", "C:\\");
        dirTree1.insertAsAChild("Windows", "C:\\");
        dirTree1.insertAsAChild("Users", "C:\\");
        dirTree1.insertAsAChild("Java","Program Files");
        dirTree1.insertAsAChild("Git","Program Files");
        dirTree1.insertAsAChild("Windows Defender","Program Files");
        dirTree1.insertAsAChild("Lander","Users");
        dirTree1.insertAsAChild("eclipse-workspace","Lander");
        dirTree1.insertAsAChild("cs140","eclipse-workspace");
        dirTree1.insertAsAChild("src","cs140");
        dirTree1.insertAsAChild("assignment05","src");
        dirTree1.print();
    }
}
