package problems;

import java.util.Random;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer resp = null;
		// Valida os parâmetros
		if (array != null && x != null) {
			// Busca o piso de X
			resp = quickSortFloorBinary(array, x, 0, array.length-1);
		}
		return resp;
	}

	// Método para buscar o k-ésimo elemento do array (recursivamente) utilizando Quick Sort
	public Integer quickSortFloorBinary(Integer[] array, int x, int leftIndex, int rightIndex) {
		// Guarda o elemento piso a ser retornado
		Integer floor = null;
		/*
		 * Em caso de restar um intervalo de tamanho 1 verifica-se o
		 */
		if (leftIndex == rightIndex && array[leftIndex] <= x) {
			floor = array[leftIndex];
		} else if (leftIndex < rightIndex) {
			// Busca o índice do pivot do intervalo
			int indexPivot = particionamento(array, leftIndex, rightIndex);

			// Verifica por busca binária se o pivot é maior, menor ou igual ao elemento.
			if (array[indexPivot] > x) {
				/*
				 * Em caso de pivot maior que o elemento X usa-se a recusão na partição da esquerda
			 	 * (pivot não incluso).
				 */
				floor = quickSortFloorBinary(array, x, leftIndex, indexPivot-1);
			} else if (array[indexPivot] < x) {
				/*
				 * Em caso de pivot menor que X utiliza-se a partição da direita com o pivot incluso.
				 */
				floor = quickSortFloorBinary(array, x, indexPivot, rightIndex);
			} else {
				// Em caso de igualdade é retornado o pŕoprio elemento.
				floor = x;
			}
		}
		// Retorna o piso de X
		return floor;
	}

	public Integer particionamento(Integer[] array, int leftIndex, int rightIndex) {
		Random gerador = new Random();
		int randomIndexPivot = gerador.nextInt(rightIndex - leftIndex + 1) + leftIndex;

		Util.swap(array, leftIndex, randomIndexPivot);

		int indexPivot = leftIndex;
		int indexNextSmaller = leftIndex;
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i].compareTo(array[indexPivot]) <= 0) {
				indexNextSmaller += 1;
				Util.swap(array, indexNextSmaller, i);
			}
		}
		Util.swap(array, indexPivot, indexNextSmaller);
		indexPivot = indexNextSmaller;
		return indexPivot;
	}

}