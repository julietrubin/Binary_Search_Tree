import java.util.ArrayList;


class BinarySearchTree<T extends Comparable <T>> {

	private class BSTNode {
		public T data;
		public BSTNode left;
		public BSTNode right;

		BSTNode(T newdata){
			data = newdata;
		}
	}

	private BSTNode root;

	public T ElementAt(int i) {
		return ElementAt(root, i);
	}

	public T ElementAt(BSTNode tree, int i) {
		if(tree == null)
			return null;

		int leftandroot = Count(tree.left) + 1;

		if(i == leftandroot)
			return tree.data;
		if (i < leftandroot)
			return ElementAt(tree.left, i);

		return ElementAt(tree.right, i - leftandroot);

	}

	public int NumBetween(T low, T high) {
		return CountTest(root, low, high);
	}

	public int CountTest(BSTNode tree, T low, T high){
		if(tree == null)
			return 0;
		if(low.compareTo(tree.data) == 0)
			return 1 + CountTest(tree.right, low, high);
		if(high.compareTo(tree.data) == 0)
			return 1 + CountTest(tree.left, low, high);
		if((low.compareTo(tree.data) > 0))
			return CountTest(tree.right, low, high);
		if((high.compareTo(tree.data) < 0))
			return CountTest(tree.left, low, high);

		else
			return 1 + CountTest(tree.right, low, high) + CountTest(tree.left, low, high);
	}

	public int Count(BSTNode tree){
		if(tree == null)
			return 0;
		return 1 + Count(tree.left) + Count(tree.right);
	}

	public int Count(){
		return Count(root);
	}

	public void Balance(){
		ArrayList<T> d = new ArrayList<T>();
		FillArray(root, d);
		root = null;
		BalancedBST(d, 0, d.size()-1);
	}

	private void BalancedBST(ArrayList<T> array, int start, int end) {
		int size = end - start;
		if(size == 0) {
			Insert(array.get(start));
			return;
		}
		if(size == 1) {
			Insert(array.get(start));
			Insert(array.get(end));
			return;
		}

		int midpoint = start + size/2;
		//System.out.println("start:" + start + " end: " + end + " midpoint: " + midpoint + " size:" + size);
		Insert(array.get(midpoint));
		BalancedBST(array, start, midpoint - 1);
		BalancedBST(array, midpoint + 1, end);
	}

	public void FillArray(BSTNode tree, ArrayList<T> d){
		if(tree != null){
			FillArray(tree.left, d);
			d.add(tree.data);
			FillArray(tree.right, d);
		}
	}

	public void Insert(T elem) {
		root = Insert(root, elem);
	}

	public boolean Find(T elem) {
		return Find(root, elem);
	}

	public void Delete(T elem) {
		root = Delete(root, elem);
	}

	public void Print() {
		Print(root);
	}

	public int Height() {
		return Height(root);
	}

	private int Height(BSTNode tree) {
		if (tree == null)
			return 0;
		return 1 + Math.max(Height(tree.left), Height(tree.right));
	}

	private boolean Find(BSTNode tree, T elem) {
		if (tree == null)
			return false;
		if (elem.compareTo(tree.data) == 0)
			return true;
		if (elem.compareTo(tree.data) < 0)
			return Find(tree.left, elem);
		else
			return Find(tree.right, elem);
	}

	private T Minimum(BSTNode tree) {
		if (tree == null)
			return null;
		if (tree.left == null)
			return tree.data;
		else
			return Minimum(tree.left);
	}

	private void Print(BSTNode tree) {
		if (tree != null) {
			Print(tree.left);
			System.out.println(tree.data);
			Print(tree.right);
		}
	}

	private BSTNode Insert(BSTNode tree, T elem) {
		if (tree == null) 
			return new BSTNode(elem);
		if (elem.compareTo(tree.data) < 0) {
			tree.left = Insert(tree.left, elem);
			return tree;
		} 
		else {
			tree.right = Insert(tree.right, elem);
			return tree;
		}
	}


	private BSTNode Delete(BSTNode tree, T elem) {
		if (tree == null) 
			return null;
		if (tree.data.compareTo(elem) == 0) {
			if (tree.left == null)
				return tree.right;
			else if (tree.right == null)
				return tree.left;
			else {
				if (tree.right.left == null) {
					tree.data = tree.right.data;
					tree.right = tree.right.right;
					return tree;
				} 
				else {         
					tree.data = RemoveSmallest(tree.right);
					return tree;
				}
			}
		} 
		else  if (elem.compareTo(tree.data) < 0) {
			tree.left = Delete(tree.left, elem);
			return tree;
		} 
		else {
			tree.right = Delete(tree.right, elem);
			return tree;
		}
	}  

	T RemoveSmallest(BSTNode tree) {
		if (tree.left.left == null) {
			T smallest = tree.left.data;
			tree.left = tree.left.right;
			return smallest;
		} 
		else 
			return RemoveSmallest(tree.left);
	}

	public static void main(String args[]){
		BinarySearchTree<Integer> t= new BinarySearchTree<Integer>();
		for (int x = 0; x < 31; x++)
			t.Insert(new Integer(x));
		System.out.println("ElementAt(9): " + t.ElementAt(new Integer(9)));
		System.out.println("NumBetween(10, 15): " + t.NumBetween(new Integer(10), new Integer(15)));
		System.out.println("Height: " + t.Height());
		t.Balance();
		System.out.println("ElementAt(5): " + t.ElementAt(new Integer(5)));
		System.out.println("Count(): " + t.Count());
		System.out.println("NumBetween(10, 15): " +t.NumBetween(new Integer(10), new Integer(15)));
		System.out.println("Count(): " + t.Count());
		System.out.println("Height: " + t.Height());
		System.out.println("Count(): " + t.Count());


	}

}