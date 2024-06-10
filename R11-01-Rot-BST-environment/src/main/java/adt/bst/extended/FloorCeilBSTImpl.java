package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer floor = null;
		if (array != null && array.length > 0) {
			this.addElements(array, 0);
			floor = this.floor(this.root, numero, null);
		}
		return floor;
	}
	/**
	 * Adiciona recursivamente os números inteiros de um array em uma BST.
	 * 
	 * @param array O array de inteiros.
	 * @param index O índice em que o elemento será adicionado.
	 */
	private void addElements(Integer[] array, int index) {
		if (index < array.length) {
			this.insert(array[index]);
			addElements(array, index+1);
		}
	}
	/**
	 * Função recursiva que busca o floor (piso) de um númeor em uma BST.
	 * 
	 * @param node O nó atual.
	 * @param numero O número que se deseja obter o piso.
	 * @param floor O último piso encontrado (null se não tiver sido encontrado)
	 * @return O piso do número.
	 */
	private Integer floor(BSTNode<Integer> node, double numero, Integer floor) {
		Integer result = floor;
		if (!node.isEmpty()) {
			if (node.getData() == numero) {
				result = node.getData();
			} else if (node.getData() < numero) {
				if (floor == null || node.getData() > floor) {
					result = node.getData();
				}
				result = floor((BSTNode<Integer>) node.getRight(), numero, result);
			} else {
				result = floor((BSTNode<Integer>) node.getLeft(), numero, result);
			}
		}
		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer ceil = null;
		if (array != null && array.length > 0) {
			this.addElements(array, 0);
			ceil = this.ceil(this.root, numero, null);
		}
		return ceil;
	}
	/**
	 * Função recursiva que busca o ceil (topo) de um númeor em uma BST.
	 * 
	 * @param node O nó atual.
	 * @param numero O número que se deseja obter o topo.
	 * @param ceil O último topo encontrado (null se não tiver sido encontrado)
	 * @return O topo do número.
	 */
	private Integer ceil(BSTNode<Integer> node, double numero, Integer ceil) {
		Integer result = ceil;
		if (!node.isEmpty()) {
			if (node.getData() == numero) {
				result = node.getData();
			} else if (node.getData() > numero) {
				if (ceil == null || node.getData() < ceil) {
					result = node.getData();
				}
				result = ceil((BSTNode<Integer>) node.getLeft(), numero, result);
			} else {
				result = ceil((BSTNode<Integer>) node.getRight(), numero, result);
			}
		}
		return result;
	}

}
