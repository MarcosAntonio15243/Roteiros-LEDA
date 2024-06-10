package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null && array.length > 0) {
			// BST auxiliar
			BSTImpl<T> bst = new BSTImpl<T>();
			// Esvazia a árvore e insere os elementos dela na BST
			drainOutAVL(bst, root);
			// Insere os elementos do array na BST
			for (T element : array) {
				bst.insert(element);
			}
			// Busca o array da ordem da BST (ordenado)
			T[] bstArrayOrder = bst.order();
			// Seta a raiz da árvore como vazia
			super.root = new BSTNode<T>();
			// Controlador da altura para inserir os elementos
			int height = 0;
			// Insere os elementos por altura para evitar rotações
			while (fillWithoutRebalance(bstArrayOrder, 0, bstArrayOrder.length-1, height)) {
				height++;
			}
		}
	}
	/**
	 * Preenche a árvore a partir de um array de elementos sem precisar balanceá-la.
	 * 
	 * @param array O array de elementos.
	 * @param leftIndex O índice da esquerda.
	 * @param rightIndex O índice da direita.
	 * @param height Atributo para controlar a busca em profundidade de cada nível da árvore para
	 * inserir o elemento no nível correspondente.
	 * @return O booleano que indica se inseriou o elemento na altura correta ou se o array já foi
	 * percorrido completamente.
	 */
	private boolean fillWithoutRebalance(T[] array, int leftIndex, int rightIndex, int height) {
		boolean resp = false;
		if (leftIndex <= rightIndex) {
			int middle = (leftIndex + rightIndex) / 2;
			if (height == 0) {
				insert(array[middle]);
				resp = true;
			} else {
				fillWithoutRebalance(array, leftIndex, middle-1, height-1);
				fillWithoutRebalance(array, middle+1, rightIndex, height-1);
			}
		}
		return resp;
	}
	/**
	 * Método auxiliar para colocar os elementos da AVL em uma BST até esvaziá-la.
	 * 
	 * @param bstImpl A BST que irá receber os elementos.
	 * @param currentNode O nó atual que está sendo percorrido na árvore.
	 */
	private void drainOutAVL(BSTImpl<T> bstImpl, BSTNode<T> currentNode) {
		if (!currentNode.isEmpty()) {
			if (currentNode.isLeaf()) {
				bstImpl.insert(currentNode.getData());
				super.remove(currentNode);
				drainOutAVL(bstImpl, (BSTNode<T>) currentNode.getParent());
			} else {
				drainOutAVL(bstImpl, (BSTNode<T>) currentNode.getLeft());
				drainOutAVL(bstImpl, (BSTNode<T>) currentNode.getRight());
			}
		}
	}

	// Sobrescrevendo o rebalance para contabilizar as rotações
	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (!this.isBalanced(node)) {
				BSTNode<T> newRoot = node;
				if (isPendingLeft(node)) {
					if (isPendingRight((BSTNode<T>) node.getLeft())) {
						Util.leftRotation((BSTNode<T>) node.getLeft());
						newRoot = Util.rightRotation(node);
						this.LRcounter++;
					} else {
						newRoot = Util.rightRotation(node);
						this.LLcounter++;
					}
				} else {
					if (this.isPendingLeft((BSTNode<T>) node.getRight())) {
						Util.rightRotation((BSTNode<T>) node.getRight());
						newRoot = Util.leftRotation(node);
						this.RLcounter++;
					} else {
						newRoot = Util.leftRotation(node);
						this.RRcounter++;
					}
				}
				if (newRoot.getData().compareTo(node.getData()) != 0 && root.getData().compareTo(node.getData()) == 0) {
					super.root = newRoot;
				} else {
					rebalance(node);
				}
			} else {
				rebalanceUp(node);
			}	
		}
	}

}
