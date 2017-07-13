package com.anastasia.app.treeviewer.tree;
import java.util.Scanner;

public class Tree39 {
	
	public static Node swap(Node root) {
		if (root == null) return null;
		
		Node tmp = root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(tmp);
		
		swap(root.getLeft());
		swap(root.getRight());

		return root;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = scanner.nextInt();
		}
		
		Node root = Tree28.build(array);
		Node.printTree(root);
		
		swap(root);
		Node.printTree(root);
	}

}
