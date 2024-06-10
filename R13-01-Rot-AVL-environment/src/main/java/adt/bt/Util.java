package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> newRoot = node;
		if (node != null && !node.isEmpty()) {
			newRoot = (BSTNode<T>) node.getRight();
			newRoot.setParent((BSTNode<T>) node.getParent());
			
			node.setRight((BSTNode<T>) newRoot.getLeft());
			newRoot.setLeft(node);

			node.setParent(newRoot);

			if (!newRoot.getParent().isEmpty()) {
				if (newRoot.getData().compareTo(((BSTNode<T>) newRoot.getParent()).getData()) > 0) {
					newRoot.getParent().setRight(newRoot);
				} else {
					newRoot.getParent().setLeft(newRoot);
				}
			}
		}
		return newRoot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> newRoot = node;
		if (node != null && !node.isEmpty()) {
			newRoot = (BSTNode<T>) node.getLeft();
			newRoot.setParent((BSTNode<T>) node.getParent());
			
			node.setLeft((BSTNode<T>) newRoot.getRight());
			newRoot.setRight(node);

			node.setParent(newRoot);

			if (!newRoot.getParent().isEmpty()) {
				if (newRoot.getData().compareTo(((BSTNode<T>) newRoot.getParent()).getData()) < 0) {
					newRoot.getParent().setLeft(newRoot);
				} else {
					newRoot.getParent().setRight(newRoot);
				}
			}
		}
		return newRoot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
