package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// Sobrescrevendo o método insert para se adaptar ao balanceamento da AVL.
	@Override
	public void insert(T element) {
		if (element != null) {
			if (super.isEmpty()) {
				super.root = super.createBTSNodeBuild(element);
			} else {
				BSTNode<T> aux = super.root;
				boolean wasInserted = false;
				while(!aux.isEmpty() && !wasInserted) {
					if (element.compareTo(aux.getData()) == 0) {
						wasInserted = true;
					} else if (element.compareTo(aux.getData()) < 0) {
						if (aux.getLeft().isEmpty()) {
							super.setInfoBTSNode((BSTNode<T>) aux.getLeft(), element, aux, new BSTNode<T>(), new BSTNode<T>());
							aux = (BSTNode<T>) aux.getLeft();
							wasInserted = true;
						} else {
							aux = (BSTNode<T>) aux.getLeft();
						}
					} else {
						if (aux.getRight().isEmpty()) {
							super.setInfoBTSNode((BSTNode<T>) aux.getRight(), element, aux, new BSTNode<T>(), new BSTNode<T>());
							aux = (BSTNode<T>) aux.getRight();
							wasInserted = true;
						} else {
							aux = (BSTNode<T>) aux.getRight();
						}
					}
				}
				this.rebalance(aux);
			}
		}
	}

	/**
	 * Método auxiliar que remove um nó a partir de 3 casos, sendo esses, respectivamente:
	 * 1. O nó é uma folha.
	 * 2. O nó possui dois filhos
	 * 3. O nó possui um único filho.
	 * Este método está sobrescrito para se adaptar aos rebalanceamentos e rotações após a remoção de algum elemento.
	 * 
	 * @param node O nó a ser removido.
	 */
	@Override
	protected void remove(BSTNode<T> node) {
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			this.removeLeaf(node);
			rebalanceUp(node);
		} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			this.removeNodeTwoChild(node);
		} else {		
			this.removeNodeOneChild(node);
			rebalanceUp(node);
		}
	}
	

	// MÉTODOS EXTRAS
	/**
	 * Método auxiliar para saber se a árvore está balanceada.
	 * 
	 * @return O booleano que indica se a árvore está balanceada (true) ou não (false).
	 */
	protected boolean isBalanced() {
		boolean isBalanced = true;
		if (super.root != null && !super.isEmpty()) {
			isBalanced = this.isBalanced(super.root);
		}
		return isBalanced;
	}
	/**
	 * Método auxiliar para saber se o nó de uma árvore está balanceado.
	 * 
	 * @param node O nó que se deseja saber se está balanceado.
	 * @return O booleano que indica se o nó está balanceado (true) ou não (false).
	 */
	protected boolean isBalanced(BSTNode<T> node) {
		boolean isBalanced = true;
		if (node != null && !node.isEmpty()) {
			isBalanced = calculateBalance(node) >= -1 && calculateBalance(node) <= 1;
		}
		return isBalanced;
	}
	/**
	 * Método auxiliar para saber se um nó está pendendo para a esquerda.
	 * 
	 * @param node O nó que se deseja saber se está pendendo para a esquerda.
	 * @return O booleano que indica se o nó está pendendo (true) ou não (false) para a esquerda.
	 */
	protected boolean isPendingLeft(BSTNode<T> node) {
		boolean isPendingLeft = false;
		if (node != null && !node.isEmpty()) {
			isPendingLeft = calculateBalance(node) > 0;
		}
		return isPendingLeft;
	}
	/**
	 * Método auxiliar para saber se um nó está pendendo para a direita.
	 * 
	 * @param node O nó que se deseja saber se está pendendo para a direita.
	 * @return O booleano que indica se o nó está pendendo (true) ou não (false) para a direita.
	 */
	protected boolean isPendingRight(BSTNode<T> node) {
		boolean isPendingRight = false;
		if (node != null && !node.isEmpty()) {
			isPendingRight = calculateBalance(node) < 0;
		}
		return isPendingRight;
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balance = 0;
		if (node != null && !node.isEmpty()) {
			balance = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}
		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (!this.isBalanced(node)) {
				BSTNode<T> newRoot = node;
				if (isPendingLeft(node)) {
					if (isPendingRight((BSTNode<T>) node.getLeft())) {
						Util.leftRotation((BSTNode<T>) node.getLeft());
					}
					newRoot = Util.rightRotation(node);
				} else {
					if (this.isPendingLeft((BSTNode<T>) node.getRight())) {
						Util.rightRotation((BSTNode<T>) node.getRight());
					}
					newRoot = Util.leftRotation(node);
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

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			rebalance((BSTNode<T>) node.getParent());
		}
	}
}
