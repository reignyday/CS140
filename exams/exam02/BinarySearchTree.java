package exam02;

public class BinarySearchTree{
	
	private int data;
	private BinarySearchTree left;
	private BinarySearchTree right;

	public BinarySearchTree(int i) {
		this.data = i;
	}

	public void insert(int element){
		if(data == element) return;
		else if(data > element) {
			if(left == null) left = new BinarySearchTree(element);
			else left.insert(element);
		}
		else{
			if(right == null) right = new BinarySearchTree(element);
			else right.insert(element);
		}
	}

	public void prettyPrint() {
		prettyPrint(0);
	}

	private void prettyPrint(int indentLevel) {
		if(left != null) left.prettyPrint(indentLevel + 1);
		for(int i = 0; i < indentLevel; i++) System.out.print("  ");
		System.out.println(data);
		if(right != null) right.prettyPrint(indentLevel + 1);
	}

	//TODO: alters the values of the leaves of the BST 
	//multiply the leaf values by n to alter them 
	public void alterLeaves(int n){
		if (this.left == null && this.right == null)
        {
			this.data *= n;
            return;
        }

		if (this.left != null)
			this.left.alterLeaves(n);
		
		if (this.right != null)
			this.right.alterLeaves(n);
	}

	public static int lcm(int a, int b){
		return lcmHelper(a, b, a, b);
	}

	//TODO: complete the helper for the lcm method.
	//returns the least common multiple of a and b
	//assume a and b are both positive values
	private static int lcmHelper(int a, int b, int aMultiple, int bMultiple){
        // edge case, would cause an infinite loop if a or b == 0
		if (a == 0 || b == 0)
            return 0;
		
		if (aMultiple == bMultiple)
			return aMultiple;
		
		if (aMultiple < bMultiple)
			return lcmHelper(a, b, aMultiple + a, bMultiple);
		
		return lcmHelper(a, b, aMultiple, bMultiple + b);
	}
}
