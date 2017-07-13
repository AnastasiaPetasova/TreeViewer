package com.anastasia.app.treeviewer.tree;

public class Node {
	
	
	private Node left;
	private Node right;
	private int value;
	
	public static void printTree(Node root){
		if(root == null){
			return;
		}
		System.out.println(root.getValue());
		printTree(root.getLeft());
		printTree(root.getRight());
	}
	
	public Node(int value){
		this.left = null;
		this.right = null;
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
