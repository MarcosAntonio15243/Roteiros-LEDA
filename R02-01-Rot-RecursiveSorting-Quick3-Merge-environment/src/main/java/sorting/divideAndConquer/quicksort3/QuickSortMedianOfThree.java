package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		// Validação para continuar a recorrência
		if (validaParametros(array, leftIndex, rightIndex)) {
			int indexPivot = particao(array, leftIndex, rightIndex);
			sort(array, leftIndex, indexPivot-1);
			sort(array, indexPivot+1, rightIndex);
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

	/*
	 * Calcula a mediana dos elementos extremos e do meio do intervalo para que
	 * A[left] < A[meio] < A[right]
	 */
	public int medianaDeTresPivot(T[] array, int leftIndex, int rightIndex) {
		int middle = (leftIndex + rightIndex) / 2;
		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, leftIndex, rightIndex);
		}
		if (array[leftIndex].compareTo(array[middle]) > 0) {
			Util.swap(array, leftIndex, middle);
		}
		if (array[middle].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, middle, rightIndex);
		}
		return middle;
	}

	// Realiza a partição do array
	public int particao(T[] array, int leftIndex, int rightIndex) {
		// Aplica a mediana de três
		int middle = medianaDeTresPivot(array, leftIndex, rightIndex);
		// Troca o pivot (índice do meio) com a penúltima posição do intervalo
		Util.swap(array, middle, rightIndex-1);

		// Guarda o indíce do pivot
		int indexPivot = rightIndex-1;
		// Guarda o índice do elemento menor ou igual ao pivot que está mais a sua esquerda
		int indexLastSmaller = rightIndex-1;

		// Itera para colocar os elementos maiores ou iguais ao pivot na sua esquerda
		for (int i = rightIndex-2; i > leftIndex; i--) {
			if (array[i].compareTo(array[indexPivot]) >= 0) {
				indexLastSmaller -= 1;
				Util.swap(array, indexLastSmaller, i);
			}
		}
		// Troca o pivot com o elemento mais a esquerda que é menor que esse
		Util.swap(array, indexPivot, indexLastSmaller);
		// Atualiza o índice do pivot
		indexPivot = indexLastSmaller;
		// Retorna o índice do pivot
		return indexPivot;
	}
	
}
