package com.anastasia.app.treeviewer.tree;
import java.util.Scanner;

public class Tree43 {
	
	public static Node executionPlusPlus(Node root) {
		if (root == null) return null;
		
		if (root.getLeft() != null && root.getRight() != null) {
			if (root.getValue() % 2 == 0) {
				root.setRight(removeTree(root.getRight()));
			} else {
				root.setLeft(removeTree(root.getLeft()));
			}
		}
		
		executionPlusPlus(root.getLeft());
		executionPlusPlus(root.getRight());
		return root;
	}
	
	public static Node removeTree(Node root) {
		if (root != null) {
			root.setLeft(removeTree(root.getLeft()));
			root.setRight(removeTree(root.getRight()));
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		Node root = Tree33.build(n);
		System.out.println("Before");
		Node.printTree(root);
		
		executionPlusPlus(root);
		System.out.println("After");
		Node.printTree(root);
	}

}
