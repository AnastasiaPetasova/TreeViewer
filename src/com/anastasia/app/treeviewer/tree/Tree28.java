package com.anastasia.app.treeviewer.tree;
import java.util.Scanner;

public class Tree28 {
	public static Node build(int... array) {
		return build(array, 0, false);
	}
	
	public static Node build(int[] array, int index, boolean besplodie) {
		if(index >= array.length){
			return null;
		}
		Node node = new Node(array[index]);
		if(!besplodie){
			node.setLeft(build(array, index + 1, true));
			node.setRight(build(array, index + 2, false));
		}
		return node;
	}
	
	public static Node addValue(Node root, int value) {
		if (root == null) {
			return new Node(value);
		}
		
		if (root.getLeft() == null) root.setLeft(addValue(root.getLeft(), value));
		else root.setRight(addValue(root.getRight(), value));
		
		return root;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = scanner.nextInt();
		}
		
		Node root = build(array);

		root = build(3, 1, 2, 4);
		System.out.println("Created tree");
		Node.printTree(root);
		
		int addingValue = scanner.nextInt();
		root = addValue(root, addingValue);
		
		System.out.println("After adding");
		Node.printTree(root);
	}

}
