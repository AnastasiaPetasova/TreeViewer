package com.anastasia.app.treeviewer.tree;
import java.util.Scanner;

public class Tree33 {
	public static Node build(int size, int depth) {
		if(size == 0) return null;

		Node node = new Node(depth);
		node.setLeft(build(size / 2, depth + 1));
		node.setRight(build(size - 1 - size / 2, depth + 1));

		return node;
	}

	public static Node build(int... array) {
		int size = array[0];
		return build(size, 0);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		Node root = build(n);
		Node.printTree(root);
	}

}
