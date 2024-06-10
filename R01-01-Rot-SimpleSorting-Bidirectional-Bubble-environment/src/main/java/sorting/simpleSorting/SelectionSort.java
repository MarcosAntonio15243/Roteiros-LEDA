package sorting.simpleSorting;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// Verificando se o array e os limites extremos são válidos
		if (array != null && leftIndex >= 0 && rightIndex >= 0 && rightIndex > leftIndex && rightIndex < array.length) {
			// Laço para iterar por cada elemento do array
			for (int i = leftIndex; i <= rightIndex; i++) {
				int indexMenor = i;
				// Laço para comparar o elemento do laço externo com os seus sucessores
				for (int j = i + 1; j <= rightIndex; j++) {
					if (array[indexMenor].compareTo(array[j]) > 0) {
						indexMenor = j;
					}
				}
				Util.swap(array, i, indexMenor);
			}
		}
	}

}
