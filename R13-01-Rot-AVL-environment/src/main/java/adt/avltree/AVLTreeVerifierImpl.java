package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		boolean isAVLTree = true;
		if (this.avlTree != null && !this.avlTree.isEmpty()) {
			isAVLTree = validateNodeAVL(this.getAVLTree(), avlTree.getRoot());
		}
		return isAVLTree && super.isBST();
	}
	protected boolean validateNodeAVL(AVLTreeImpl<T> avlTree, BSTNode<T> currentNode) {
		boolean isAVLTree = true;
		if (!currentNode.isEmpty() && !currentNode.isLeaf()) {
			if (avlTree.calculateBalance(currentNode) < -1 || avlTree.calculateBalance(currentNode) > 1) {
				isAVLTree = false;
			} else {
				isAVLTree = validateNodeAVL(avlTree, (BSTNode<T>) currentNode.getLeft()) && validateNodeAVL(avlTree, (BSTNode<T>) currentNode.getRight());
			}
		}
		return isAVLTree;
	}

}
