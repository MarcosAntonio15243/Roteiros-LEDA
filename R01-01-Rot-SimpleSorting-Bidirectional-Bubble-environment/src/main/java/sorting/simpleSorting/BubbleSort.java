package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		// Verificando se o array e os limites extremos são válidos
		if (array != null && leftIndex >= 0 && rightIndex >= 0 && rightIndex > leftIndex && rightIndex < array.length) {

			// Loop para iterar em cada elemento do array (exceto o último)
			for (int i = leftIndex; i < rightIndex; i++) {

				// Loop para comparar os elementos do array
				for (int j = leftIndex; j < rightIndex - i; j++) {
					
					// Levando o maior elemento do intervalo para a direita do array
					if (array[j].compareTo(array[j+1]) > 0) {
						Util.swap(array, j, j+1);
					}

				}
			}
		}

	}
	
}
