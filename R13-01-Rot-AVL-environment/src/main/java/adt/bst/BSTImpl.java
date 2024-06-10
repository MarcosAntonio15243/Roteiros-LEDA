package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(this.root);
	}
	public int height(BSTNode<T> node) {
		int height = -1;
		if (!node.isEmpty()) {
			height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> elementFound = this.root;
		if (element != null && !this.root.isEmpty()) {
			boolean foundElement = false;
			while (!elementFound.isEmpty() && !foundElement) {
				if (element.compareTo(elementFound.getData()) == 0) {
					foundElement = true;
				} else if (element.compareTo(elementFound.getData()) < 0) {
					elementFound = (BSTNode<T>) elementFound.getLeft();
				} else {
					elementFound = (BSTNode<T>) elementFound.getRight();
				}
			}
		}
		return elementFound;
	}

	// Seta os dados de um BTSNode
	public void setInfoBTSNode(BSTNode<T> node, T element, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
		node.setData(element);
		node.setParent(parent);
		node.setLeft(left);
		node.setRight(right);
	}

	// Cria e retorna um novo BTSNode a partir do data
	@SuppressWarnings("unchecked")
	public BSTNode<T> createBTSNodeBuild(T element) {
		return (BSTNode<T>) new BSTNode.Builder<T>()
							.data(element)
							.parent(new BSTNode<T>())
							.left(new BSTNode<T>())
							.right(new BSTNode<T>())
							.build();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.root = createBTSNodeBuild(element);
			} else {
				BSTNode<T> aux = this.root;
				boolean wasInserted = false;
				while (!aux.isEmpty() && !wasInserted) {
					if (element.compareTo(aux.getData()) == 0) {
						wasInserted = true;
					} else if (element.compareTo(aux.getData()) < 0) {
						if (aux.getLeft().isEmpty()) {
							setInfoBTSNode((BSTNode<T>) aux.getLeft(), element, aux, new BSTNode<T>(), new BSTNode<T>());
							wasInserted = true;
						} else {
							aux = (BSTNode<T>) aux.getLeft();
						}
					} else {
						if (aux.getRight().isEmpty()) {
							setInfoBTSNode((BSTNode<T>) aux.getRight(), element, aux, new BSTNode<T>(), new BSTNode<T>());
							wasInserted = true;
						} else {
							aux = (BSTNode<T>) aux.getRight();
						}
					}
				}
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> maximum = null;
		if (!this.root.isEmpty()) {
			maximum = this.maximum(this.root);
		}
		return maximum;
	}
	/**
	 * Busca o máximo em uma BST a partir de um nó atuando como raiz.
	 * 
	 * @param node O nó inicial
	 * @return O máximo encontrado.
	 */
	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> max = node;
		if (!node.isEmpty()) {
			while (!max.getRight().isEmpty()) {
				max = (BSTNode<T>) max.getRight();
			}
		}
		return max;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> minimum = null;
		if (!this.root.isEmpty()) {
			minimum = this.minimum(this.root);
		}
		return minimum;
	}
	/**
	 * Busca o mínimo em uma BST a partir de um nó atuando como raiz.
	 * 
	 * @param node O nó inicial
	 * @return O mínimo encontrado.
	 */
	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> min = node;
		if (!node.isEmpty()) {
			while (!min.getLeft().isEmpty()) {
				min = (BSTNode<T>) min.getLeft();
			}
		}
		return min;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> sucessor = new BSTNode<T>();
		if (element != null && !this.root.isEmpty() && !this.search(element).isEmpty()) {
			BSTNode<T> nodeElement = this.search(element);
			if (!nodeElement.getRight().isEmpty()) {
				sucessor = this.minimum((BSTNode<T>) nodeElement.getRight());
			} else {
				sucessor = (BSTNode<T>) nodeElement.getParent();
				boolean foundSucessor = false;
				while (!sucessor.isEmpty() && !foundSucessor) {
					if (sucessor.getData().compareTo(nodeElement.getData()) > 0) {
						foundSucessor = true;
					} else {
						sucessor = (BSTNode<T>) sucessor.getParent();
					}
				}
			}
		}
		return sucessor.isEmpty() ? null : sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> predecessor = new BSTNode<T>();
		if (element != null && !this.root.isEmpty() && !this.search(element).isEmpty()) {
			BSTNode<T> nodeElement = this.search(element);
			if (!nodeElement.getLeft().isEmpty()) {
				predecessor = this.maximum((BSTNode<T>) nodeElement.getLeft());
			} else {
				predecessor = (BSTNode<T>) nodeElement.getParent();
				boolean foundPredecessor = false;
				while (!predecessor.isEmpty() && !foundPredecessor) {
					if (predecessor.getData().compareTo(nodeElement.getData()) < 0) {
						foundPredecessor = true;
					} else {
						predecessor = (BSTNode<T>) predecessor.getParent();
					}
				}
			}
		}
		return predecessor.isEmpty() ? null : predecessor;
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.root.isEmpty() && !this.search(element).isEmpty()) {
			BSTNode<T> nodeElement = this.search(element);
			this.remove(nodeElement);
		}
	}
	/**
	 * Método auxiliar que remove um nó a partir de 3 casos, sendo esses, respectivamente:
	 * 1. O nó é uma folha.
	 * 2. O nó possui dois filhos
	 * 3. O nó possui um único filho.
	 * 
	 * @param node O nó a ser removido.
	 */
	protected void remove(BSTNode<T> node) {
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			this.removeLeaf(node);
		} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			this.removeNodeTwoChild(node);
		} else {		
			this.removeNodeOneChild(node);
		}
	}
	/**
	 * Método auxiliar que remove um nó folha da árvore.
	 * 
	 * @param node O nó folha a ser removido.
	 */
	protected void removeLeaf(BSTNode<T> node) {
		if (node.getData().compareTo(this.root.getData()) == 0) {
			this.root.setData(null);
		} else {
			if (node.getData().compareTo(node.getParent().getData()) < 0) {
				node.getParent().setLeft(new BSTNode<T>());
			} else {
				node.getParent().setRight(new BSTNode<T>());
			}
		}
	}
	/**
	 * Método auxiliar que remove um nó com um único filho.
	 * 
	 * @param node O nó com único filho a ser removido.
	 */
	protected void removeNodeOneChild(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			if (node.getData().compareTo(this.root.getData()) == 0) {
				((BSTNode<T>) node.getLeft()).setParent(new BSTNode<T>());
				this.root = ((BSTNode<T>) node.getLeft());
			} else {
				((BSTNode<T>) node.getLeft()).setParent((BSTNode<T>) node.getParent());
				if (node.getData().compareTo(((BSTNode<T>) node.getParent()).getData()) < 0) {
					((BSTNode<T>) node.getParent()).setLeft((BSTNode<T>) node.getLeft());
				} else {
					((BSTNode<T>) node.getParent()).setRight((BSTNode<T>) node.getLeft());
				}
			}
		} else {
			if (node.getData().compareTo(this.root.getData()) == 0) {
				((BSTNode<T>) node.getRight()).setParent(new BSTNode<T>());
				this.root = ((BSTNode<T>) node.getRight());
			} else {
				((BSTNode<T>) node.getRight()).setParent((BSTNode<T>) node.getParent());
				if (node.getData().compareTo(((BSTNode<T>) node.getParent()).getData()) < 0) {
					((BSTNode<T>) node.getParent()).setLeft((BSTNode<T>) node.getRight());
				} else {
					((BSTNode<T>) node.getParent()).setRight((BSTNode<T>) node.getRight());
				}
			}
		}
	}
	/**
	 * Método auxiliar que remove um nó com dois filhos.
	 * 
	 * @param node O nó com dois filhos a ser removido.
	 */
	protected void removeNodeTwoChild(BSTNode<T> node) {
		BSTNode<T> sucessor = this.sucessor(node.getData());
		T newData = sucessor.getData();
		this.remove(sucessor);
		node.setData(newData);
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (!this.root.isEmpty()) {
			this.preOrder(this.root, array, 0);
		}
		return array;
	}
	private int preOrder(BSTNode<T> currentNode, T[] array, int index) {
		int i = index;
		if (!currentNode.isEmpty()) {
			array[i++] = currentNode.getData();
			i = preOrder((BSTNode<T>) currentNode.getLeft(), array, i);
			i = preOrder((BSTNode<T>) currentNode.getRight(), array, i);
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		if (!this.root.isEmpty()) {
			this.order(this.root, array, 0);
		}
		return array;
	}
	private int order(BSTNode<T> currentNode, T[] array, int index) {
		int i = index;
		if (!currentNode.isEmpty()) {
			i = order((BSTNode<T>) currentNode.getLeft(), array, i);
			array[i++] = currentNode.getData();
			i = order((BSTNode<T>) currentNode.getRight(), array, i);
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (!this.root.isEmpty()) {
			this.postOrder(this.root, array, 0);
		}
		return array;
	}
	private int postOrder(BSTNode<T> currentNode, T[] array, int index) {
		int i = index;
		if (!currentNode.isEmpty()) {
			i = postOrder((BSTNode<T>) currentNode.getLeft(), array, i);
			i = postOrder((BSTNode<T>) currentNode.getRight(), array, i);
			array[i++] = currentNode.getData();
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
