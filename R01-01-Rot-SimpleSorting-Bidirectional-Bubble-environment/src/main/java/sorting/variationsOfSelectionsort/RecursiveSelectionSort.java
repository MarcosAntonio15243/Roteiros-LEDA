package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// Verifica se o array e os indices extremos são válidos
		if (array != null && leftIndex >= 0 && rightIndex >= 0 && leftIndex < rightIndex && rightIndex < array.length) {
			int indexMenor = getIndexMenorRecursivo(array, leftIndex+1, rightIndex, leftIndex);
			Util.swap(array, leftIndex, indexMenor);
			sort(array, leftIndex+1, rightIndex);
		}
	}


	// Busca o indice do menor elemento do intervalo e retorna-o
	private int getIndexMenorRecursivo(T[] array, int leftIndex, int rightIndex, int indexMenor) {
		// Condição para a recorrência continuar
		if (leftIndex <= rightIndex) {
			if (array[leftIndex].compareTo(array[indexMenor]) < 0) {
				indexMenor = leftIndex;
			}
			return getIndexMenorRecursivo(array, leftIndex+1, rightIndex, indexMenor);
		} else {
			return indexMenor;
		}
	}

}
