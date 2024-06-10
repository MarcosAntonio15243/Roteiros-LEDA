package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		boolean equals = false;
		if (tree1 != null && tree2 != null) {
			equals = this.equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}
		return equals;
	}
	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		boolean equals = true;
		if (!node1.isEmpty() && !node2.isEmpty()) {
			if (node1.getData().compareTo(node2.getData()) != 0) {
				equals = false;
			} else {
				equals = equals && equals((BSTNode<T>) node1.getLeft(),(BSTNode<T>) node2.getLeft()) && equals((BSTNode<T>) node1.getRight(),(BSTNode<T>) node2.getRight());
			}
		} else if (node1.isEmpty() && !node2.isEmpty() || !node1.isEmpty() && node2.isEmpty()) {
			equals = false;
		}
		return equals;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		boolean isSimilar = false;
		if (tree1 != null && tree2 != null) {
			isSimilar = this.isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}
		return isSimilar;
	}
	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean isSimilar = true;
		if (!node1.isEmpty() && !node2.isEmpty()) {
			isSimilar = isSimilar && isSimilar((BSTNode<T>) node1.getLeft(),(BSTNode<T>) node2.getLeft()) && isSimilar((BSTNode<T>) node1.getRight(),(BSTNode<T>) node2.getRight());
		} else if (node1.isEmpty() && !node2.isEmpty() || !node1.isEmpty() && node2.isEmpty()) {
			isSimilar = false;
		}
		return isSimilar;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T orderStatistic = null;
		if (tree != null && !tree.isEmpty() && k > 0 && k <= tree.size()) {
			BSTNode<T> minimum = tree.minimum();
			orderStatistic = this.orderStatistic(tree, minimum, k-1);
		}
		return orderStatistic;
	}
	private T orderStatistic(BST<T> tree, BSTNode<T> node, int k) {
		T orderStatistic = null;
		if (k == 0) {
			orderStatistic = node.getData();
		} else {
			BSTNode<T> sucessor = tree.sucessor(node.getData());
			orderStatistic = orderStatistic(tree, sucessor, k-1);
		}
		return orderStatistic;
	}

}
