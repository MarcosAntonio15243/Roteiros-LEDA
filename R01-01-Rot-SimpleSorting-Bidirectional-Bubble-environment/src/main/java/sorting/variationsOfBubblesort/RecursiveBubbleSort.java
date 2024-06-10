package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do bubble sort. Você deve implementar apenas esse
	 * método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		// Verifica se o array e os indices extremos são válidos
		if (array != null && leftIndex >= 0 && rightIndex >= 0 && leftIndex < rightIndex && rightIndex < array.length) {
			swapRecursive(array, leftIndex, rightIndex);
			sort(array, leftIndex, rightIndex-1);
		}

	}

	// Método para levar o maior elemento do intervado para o lado direito do array de maneira recursiva
	private void swapRecursive(T[] array, int leftIndex, int rightIndex) {

		// Condições para a recursão continuar
		if (leftIndex + 1 <= rightIndex) {
			if (array[leftIndex].compareTo(array[leftIndex + 1]) > 0) {
				Util.swap(array, leftIndex, leftIndex + 1);
			}
			swapRecursive(array, leftIndex+1, rightIndex);
		}

	}

}
