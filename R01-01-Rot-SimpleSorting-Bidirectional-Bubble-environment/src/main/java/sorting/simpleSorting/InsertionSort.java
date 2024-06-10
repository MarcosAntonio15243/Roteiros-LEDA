package sorting.simpleSorting;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		// Verificando se o array e os limites extremos são válidos
		if (array != null && leftIndex >= 0 && rightIndex >= 0 && rightIndex > leftIndex && rightIndex < array.length) {

			// Laço para iterar por cada elemento do array (exceto o primeiro)
			for (int i = leftIndex + 1; i <= rightIndex; i++) {

				boolean isMaior = false;
				int j = i;
				// Laço para comparar o elemento atual com os antecessores
				while (j > leftIndex && !isMaior) {
					if (array[j].compareTo(array[j-1]) > 0) {
						isMaior = true;
					} else if (array[j].compareTo(array[j-1]) < 0) {
						Util.swap(array, j, j-1);
					}
					j--;
				}

			}

		}

	}
	
}
