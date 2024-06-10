package orderStatistic;

import java.util.Random;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	// Busca os k-ésimos maiores elementos do array
	@Override
	public T[] getKLargest(T[] array, int k) {
		// Cria um array vazio dos maiores elementos
		T[] biggestElements = (T[]) new Comparable[]{};
		// Valida os parâmetros
		if (array != null && array.length > 0 && k > 0 && k <= array.length) {
			// Atualiza o array dos maiores elementos para o tamanho K
			biggestElements = (T[]) new Comparable[k];
			// Índice auxilizar para inserir os maiores elementos no array
			int auxIndex = 0;
			// Laço para buscar os k-ésimos maiores elementos do array
			for (int i = array.length - k + 1; i <= array.length; i++) {
				// Busca o k-ésimo maior elemento e adiciona-o no array
				T element = orderStatistics(array, i);
				if (element != null) {
					biggestElements[auxIndex++] = element;
				}
			}
		}
		// Retorna o array dos k-ésimos maiores elementos
		return biggestElements;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		return quickSortOrderStatistics(array, k, 0, array.length-1);
	}

	// Método para buscar o k-ésimo elemento do array (recursivamente) utilizando Quick Sort
	public T quickSortOrderStatistics(T[] array, int k, int leftIndex, int rightIndex) {
		T elemento = null;
		if (leftIndex <= rightIndex) {
			int indexPivot = particionamento(array, leftIndex, rightIndex);
			if (k < indexPivot+1) {
				elemento = quickSortOrderStatistics(array, k, leftIndex, indexPivot-1);
			} else if (k > indexPivot+1) {
				elemento = quickSortOrderStatistics(array, k, indexPivot+1, rightIndex);
			} else {
				elemento = array[indexPivot];
			}
		}
		return elemento;
	}

	public int particionamento(T[] array, int leftIndex, int rightIndex) {
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
