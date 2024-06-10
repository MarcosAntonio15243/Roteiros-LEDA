package orderStatistic;

import java.util.Random;

import util.Util;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 *
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author adalberto e campelo
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calcular o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os dados em duas partes, baseando-se no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso reduz a complexidade de O(n.log n) para O(n).
	 *
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 *
	 *
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento
	 *            este array normalmente nao esta ordenado
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 *
	 */
	public T quickSelect(T[] array, int k) {
		T resp = null;
		// Valida os parâmetros
		if (array != null && array.length > 0 && k > 0 && k <= array.length) {
			// Busca o k-ésimo elemento
			resp = quickSelectRecursive(array, k, 0, array.length-1);
		}
		return resp;
	}

	// Método para buscar a k-ésima ordem do array (recursivamente) utilizando o Quick Sort
	public T quickSelectRecursive(T[] array, int k, int leftIndex, int rightIndex) {
		T elemento = null;
		if (leftIndex <= rightIndex) {
		// if (leftIndex == rightIndex) {
		// 	elemento = array[leftIndex];
		// } else {
			// Busca o índice do pivot do intervalo
			int indexPivot = particionamento(array, leftIndex, rightIndex);
			// Atualiza o k-ésimo menor elemento que se deseja encontrar
			if (k < indexPivot+1) {
				elemento = quickSelectRecursive(array, k, leftIndex, indexPivot-1);
			} else if (k > indexPivot+1) {
				elemento = quickSelectRecursive(array, k, indexPivot+1, rightIndex);
			} else {
				elemento = array[indexPivot];
			}
		}
		return elemento;
	}

	// Método para realizar o particionamento do Quick Sort
	public int particionamento(T[] array, int leftIndex, int rightIndex) {
		// Gera um indice aleatório que representa a posição do pivot
		Random gerador = new Random();
		int randomIndexPivot = gerador.nextInt(rightIndex - leftIndex + 1) + leftIndex;

		// Troca o pivot com o primeiro elemento do intervalo (elemento mais à esquerda)
		Util.swap(array, leftIndex, randomIndexPivot);

		// Guarda o índice do pivot
		int indexPivot = leftIndex;
		// Guarda o índice do último inteiro menor ou igual ao pivot
		int indexNextSmaller = leftIndex;
		// Busca os elementos menores que o pivot e colocam à sua direita
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i].compareTo(array[indexPivot]) <= 0) {
				indexNextSmaller += 1;
				Util.swap(array, indexNextSmaller, i);
			}
		}
		// Troca o pivot com o último elemento menor ou igual a ele
		Util.swap(array, indexPivot, indexNextSmaller);
		// Atualiza o índice do pivot
		indexPivot = indexNextSmaller;
		// Retorna o índice do pivot
		return indexPivot;
	}

}