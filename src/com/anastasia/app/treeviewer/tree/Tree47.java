package com.anastasia.app.treeviewer.tree;
import java.util.Scanner;

public class Tree47 {

	public static Node extend(Node root) {
		int depth = getDepth(root);
//		System.out.println("Depth = " + depth);

		return extend(root, depth);
	}

	public static int getDepth(Node root) {
		if (root == null) return 0;
		
		int leftDepth = getDepth(root.getRight());
		int rightDepth = getDepth(root.getLeft());
		
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	public static Node extend(Node root, int distanceToHell) {
		if (distanceToHell == 0) return null;
		
		if (root == null) {
			root = new Node(-1);
		}
		
		root.setLeft(extend(root.getLeft(), distanceToHell - 1));
		root.setRight(extend(root.getRight(), distanceToHell - 1));
		
		return root;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		Node root = Tree33.build(n);
		System.out.println("Before");
		Node.printTree(root);

		root = extend(root);
		System.out.println("After");
		Node.printTree(root);
	}

}
