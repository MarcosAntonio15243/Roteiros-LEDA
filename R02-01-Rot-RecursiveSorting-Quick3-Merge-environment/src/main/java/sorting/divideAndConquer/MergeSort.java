package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// Validações para continuar a recursão
		if (validaParametros(array, leftIndex, rightIndex)) {
			// indice do meio
			int middle = (rightIndex + leftIndex) / 2;
			// Aplica o merge sort as partições
			sort(array, leftIndex, middle);
			sort(array, middle+1, rightIndex);
			// Aplica o merge as partições
			merge(array, leftIndex, rightIndex);
		}
	}

	// Método auxiliar para validar os parâmetros
	private boolean validaParametros(T[] array, int leftIndex, int rightIndex) {
		boolean resp = false;
		if (array != null && array.length > 1 && leftIndex >= 0 && rightIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex) {
			resp = true;
		}
		return resp;
	}

	// Método para realizar o merge
	public void merge(T[] array, int leftIndex, int rightIndex) {
		
		// Clonando os elementos para ordenar
		int size = rightIndex - leftIndex + 1;
		// Indice auxiliar para busca de elementos do intervalo no array original
		int auxIndex = leftIndex;
		// Criando e inserindo elementos no array auxiliar
		T[] helper = (T[]) new Comparable[size];
		for (int k = 0; k < helper.length; k++) {
			helper[k] = array[auxIndex++];
		}

		// Colocando os elementos ordenados do array auxiliar para o array principal
		int index = leftIndex;
		int i = 0;
		int middleHelper = (helper.length - 1) / 2;
		int j = (middleHelper) + 1;
		while (i <= middleHelper && j <= helper.length - 1) {
			if (helper[i].compareTo(helper[j]) <= 0) {
				array[index++] = helper[i++];
			} else {
				array[index++] = helper[j++];
			}
		}
		
		// Adicionando elementos restantes
		while (i <= middleHelper) {
			array[index++] = helper[i++];
		}
		while (j <= helper.length - 1) {
			array[index++] = helper[j++];
		}
	}

}
