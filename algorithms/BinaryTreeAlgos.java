package algorithms;

import data.binarytree.BinaryTree;
import data.binarytree.Node;
import java.util.ArrayList;

public class BinaryTreeAlgos {
	public static void main(String[] args) {
		System.out.println("Test insertion code");
		BinaryTree b1 = new BinaryTree (new int[]{5,3,8,2,6,7,4,8,5,0,1,3});
		System.out.println(b1);
		b1.treeInsert (new int[]{3,7,9,1});
		System.out.println(b1);
		b1.treeInsert(-1);
		System.out.println(b1);
		
		System.out.println("Test breadth-first code");
		b1.breadthFirst();
		System.out.println();
		
		System.out.println("Test least common ancestor code");
		System.out.println (leastCommonAncestor(b1,0,9));
		System.out.println (leastCommonAncestor(b1,9,7));
		System.out.println();
		
		System.out.println("Test left view code");
		BinaryTree b2 = new BinaryTree (new int[]{5,3,8,2,6,7,4,8,5,0,1,3});
		System.out.println(b2);
		leftView(b2);
		System.out.println();
		
		System.out.println("Test reverse and mirror code");
		BinaryTree b3 = new BinaryTree (new int[]{5,3,8,2,6,7,4,8,5,0,1,3});
		System.out.println(b3);
		reverse(b3);
		System.out.println(b3);
		System.out.println("Is mirror: " + isMirror(b2,b3));
		System.out.println();

		System.out.println("Test all cases of second largest");
		System.out.println(secondLargest(b2));
		System.out.println(secondLargestInorder(b2));
		System.out.println();
		BinaryTree b4 = new BinaryTree (new int[]{});
		System.out.println(secondLargest(b4));
		System.out.println(secondLargestInorder(b4));
		System.out.println();
		BinaryTree b5 = new BinaryTree (new int[]{1});
		System.out.println(secondLargest(b5));
		System.out.println(secondLargestInorder(b5));
		System.out.println();
		BinaryTree b6 = new BinaryTree (new int[]{8,7});
		System.out.println(secondLargest(b6));
		System.out.println(secondLargestInorder(b6));
		System.out.println();
		BinaryTree b7 = new BinaryTree (new int[]{8,6,7});
		System.out.println(secondLargest(b7));
		System.out.println(secondLargestInorder(b7));
		System.out.println();
		BinaryTree b8 = new BinaryTree (new int[]{8,5,7,6});
		System.out.println(secondLargest(b8));
		System.out.println(secondLargestInorder(b8));
		System.out.println();
		BinaryTree b9 = new BinaryTree (new int[]{8,5,7,6,9});
		System.out.println(secondLargest(b9));
		System.out.println(secondLargestInorder(b9));
		System.out.println();
		BinaryTree b10 = new BinaryTree (new int[]{8,5,7,6,10,9});
		System.out.println(secondLargest(b10));
		System.out.println(secondLargestInorder(b10));
		System.out.println();
		BinaryTree b11 = new BinaryTree (new int[]{8,5,7,6,10,9,13,12});
		System.out.println(secondLargest(b11));
		System.out.println(secondLargestInorder(b11));
		System.out.println();
		BinaryTree b12 = new BinaryTree (new int[]{8,5,7,6,10,9,12,13});
		System.out.println(secondLargest(b12));
		System.out.println(secondLargestInorder(b12));
		System.out.println();
		
	}

	public static void inOrder (BinaryTree btree) {
		System.out.println(btree.toString());
	}
	
	public static boolean isMirror (BinaryTree b1, BinaryTree b2) {
		return isMirror(b1.getRoot(), b2.getRoot());
	}
	
	public static boolean isMirror (Node n1, Node n2) {
		if (n1 == null && n2 == null)
			return true;
		else if (n1 == null || n2 == null)
			return false;
		else if (n1.getItem() != n2.getItem())
			return false;
		else if (isMirror(n1.getLeft(), n2.getRight()) && isMirror(n1.getRight(), n2.getLeft()))
			return true;
		else
			return false;
	}
	
	public static void reverse (BinaryTree b) {
		reverse(b.getRoot());
	}
	
	public static void reverse (Node n) {
		if (n == null)
			return;
		reverse(n.getLeft());
		reverse(n.getRight());
		
		Node t = n.getLeft();
		n.setLeft(n.getRight());
		n.setRight(t);
	}
	
	public static void leftView (BinaryTree b) {
		System.out.println ("Max depth: " + leftView(b.getRoot(), 0, -1));
	}
	
	public static int leftView (Node n, int depth, int maxDepth) {
		if (n == null)
			return maxDepth;
		if (maxDepth < depth) {
			System.out.println(n + " depth: " + depth);
			maxDepth = depth;
		}
		maxDepth = leftView (n.getLeft(), depth+1, maxDepth);
		maxDepth = leftView (n.getRight(), depth+1, maxDepth);
		return maxDepth;
	}
	
	public static Node secondLargestInorder (BinaryTree b) {
		FindSecondHolder holder = new FindSecondHolder();
		secondLargestInorder (b.getRoot(), holder);
		return holder.secondLargest; 
	}
	
	private static class FindSecondHolder {
		public Node largest, secondLargest;
	}
	
	private static void secondLargestInorder (Node n, FindSecondHolder findSecond) {
		if (n == null)
			return;
		secondLargestInorder (n.getRight(), findSecond);
		if (findSecond.largest != null) {
			if (findSecond.secondLargest != null)
				return;
			else
				findSecond.secondLargest = n;
		} else
			findSecond.largest = n;
		secondLargestInorder (n.getLeft(), findSecond);
	}
	
	public static Node secondLargest (BinaryTree b) {
		Node n = b.getRoot();
		if (n == null)
			return null;
		if (n.getRight() == null)
			return largest (n.getLeft());
		Node parent = null;
		while (n.getRight() != null) {
			parent = n;
			n = n.getRight();
		}
		if (n.getLeft() == null)
			return parent;
		return largest (n.getLeft());
	}
	
	public static Node largest (Node n) {
		if (n == null)
			return null;
		while (n.getRight() != null)
			n = n.getRight();
		return n;
	}
	
	public static Node leastCommonAncestor (BinaryTree b, int x, int y) {
		ThreeArrayLists paths = new ThreeArrayLists();
		paths.path = new ArrayList<Node>();
		findPaths (b.getRoot(), paths, x, y);
		if (paths.p1 == null || paths.p2 == null)
			return null;
		int i=0;
		while (i<paths.p1.size() && i<paths.p2.size() && paths.p1.get(i) == paths.p2.get(i))
			++i;
		return paths.p1.get(i-1);
	}
	
	private static class ThreeArrayLists {
		public ArrayList<Node> path, p1, p2;
	}
	
	private static void findPaths (Node n, ThreeArrayLists paths, int x, int y) {
		if (n == null || paths.p1 !=null && paths.p2 !=null)
			return;
		paths.path.add (n);
		if (n.getItem() == x && paths.p1 == null)
			paths.p1 = (ArrayList<Node>)paths.path.clone();
		if (n.getItem() == y && paths.p2 == null)
			paths.p2 = (ArrayList<Node>)paths.path.clone();
		findPaths (n.getLeft(), paths, x, y);
		findPaths (n.getRight(), paths, x, y);
		paths.path.remove (paths.path.size()-1);
	}
	
}
