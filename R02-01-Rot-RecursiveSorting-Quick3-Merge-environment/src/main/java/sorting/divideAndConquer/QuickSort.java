package sorting.divideAndConquer;

import java.util.Random;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		// Validação dos parâmetros para continuação da recursão
		if (validaParametros(array, leftIndex, rightIndex)) {
			
			// Pega o índice atual do pivot
			int indexPivot = particao(array, leftIndex, rightIndex);

			// Particiona o intervalo do array com os elementos menores que o pivot
			sort(array, leftIndex, indexPivot - 1);

			// Particiona o intervalo do array com os elementos maiores que o pivot
			sort(array, indexPivot + 1, rightIndex);

		}
	}

	// Método auxiliar para validar os parâmetros
	private boolean validaParametros(T[] array, int leftIndex, int rightIndex) {
		boolean resp = false;
		if (array != null && array.length > 0 && leftIndex >= 0 && rightIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex) {
			resp = true;
		}
		return resp;
	}

	// Método para realizar a partição
	public int particao(T[] array, int leftIndex, int rightIndex) {
		
		// Gera um indice aleatório que representa a posição do pivot
		Random gerador = new Random();
		int randomIndexPivot = gerador.nextInt(rightIndex - leftIndex + 1) + leftIndex;

		// Troca o pivot com o primeiro elemento do intervalo
		Util.swap(array, leftIndex, randomIndexPivot);

		// Guarda o indice do pivot
		int indexPivot = leftIndex;
		// Guarda o indice do último elemento menor ou igual ao pivot
		int indexLastSmaller = leftIndex;

		// Laço para buscar os elementos menores que o pivot e colocá-los na sua frente
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i].compareTo(array[indexPivot]) <= 0) {
				Util.swap(array, ++indexLastSmaller, i);
			}
		}

		// Troca o pivot com o último elemento menor ou igual a ele
		Util.swap(array, indexPivot, indexLastSmaller);

		// Atualiza o indice do pivot
		indexPivot = indexLastSmaller;

		// Retorna o indice do pivot
		return indexPivot;

	}

}
