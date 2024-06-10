package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		boolean isBST = false;
		if (this.bst != null && !this.bst.isEmpty()) {
			isBST = this.validateNodeBST(this.getBSt().getRoot());
		}
		return isBST;
	}
	private boolean validateNodeBST(BSTNode<T> currentNode) {
		boolean isValid = true;
		if (!currentNode.isEmpty() && !currentNode.isLeaf()) {
			if (!currentNode.getLeft().isEmpty() && currentNode.getLeft().getData().compareTo(currentNode.getData()) > 0 || !currentNode.getRight().isEmpty() && currentNode.getRight().getData().compareTo(currentNode.getData()) < 0) {
				isValid = false;
			} else {
				isValid = validateNodeBST((BSTNode<T>) currentNode.getLeft()) && validateNodeBST((BSTNode<T>) currentNode.getRight());
			}
		}
		return isValid;
	}
	
}
