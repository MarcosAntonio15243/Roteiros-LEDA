package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		/*
		 * Zerando os contadores sempre que o método sort(T[] array) for chamado para
		 * não influenciar
		 */
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		// Validações para continuar a recursão
		if (validaParametros(array, leftIndex, rightIndex)) {
			sortBySize(array, leftIndex, rightIndex);
		}
	}
	
	// Método para ordenar o array por tamanho
	private void sortBySize(T[] array, int leftIndex, int rightIndex) {
		if (validaParametros(array, leftIndex, rightIndex)) {
			if (rightIndex - leftIndex + 1 > SIZE_LIMIT) {
				mergeSort(array, leftIndex, rightIndex);
			} else {
				insertionSort(array, leftIndex, rightIndex);
			}
		}
	}

	// Método auxiliar para validar os parâmetros
	private boolean validaParametros(T[] array, int leftIndex, int rightIndex) {
		boolean resp = false;
		if (array != null && array.length > 0 && rightIndex < array.length && leftIndex >= 0 && rightIndex >= 0 && leftIndex <= rightIndex && rightIndex - leftIndex + 1 >= 1) {
			resp = true;
		}
		return resp;
	}

	// Método para realizar o MergeSort
	public void mergeSort(T[] array, int leftIndex, int rightIndex) {
		int middle = (leftIndex + rightIndex) / 2;
		sortBySize(array, leftIndex, middle);
		sortBySize(array, middle + 1, rightIndex);
		merge(array, leftIndex, rightIndex);
	}

	// Método para realizar o merge do MergeSort
	public void merge(T[] array, int leftIndex, int rightIndex) {
		// Calcula o tamanho do intervalo que se deseja ordenar
		int size = rightIndex - leftIndex + 1;
		int auxIndex = leftIndex;
		T[] helper = (T[]) new Comparable[size];
		for (int k = 0; k < helper.length; k++) {
			helper[k] = array[auxIndex++];
		}

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

		while (i <= middleHelper) {
			array[index++] = helper[i++];
		}
		while (j <= helper.length - 1) {
			array[index++] = helper[j++];
		}

		// Incrementa +1 no número de merges realizados
		MERGESORT_APPLICATIONS++;
	}

	// Método para realização do InsertionSort
	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			int j = i;
			while (j > leftIndex && array[j].compareTo(array[j-1]) < 0) {
				Util.swap(array, j, j-1);
				j--;
			}
		}

		// Incrementa +1 no número de InsertionSort realizados
		INSERTIONSORT_APPLICATIONS++;
	}

}
