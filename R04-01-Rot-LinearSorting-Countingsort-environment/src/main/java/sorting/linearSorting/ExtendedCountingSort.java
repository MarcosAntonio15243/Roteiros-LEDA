package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		// Valida os parâmetros
		if (validaParametros(array, leftIndex, rightIndex)) {

			// Busca o maior elemento do intervalo do array
			int maior = getMaior(array, leftIndex, rightIndex);
			// Busca o menor elemento do intervalo do array
			int menor = getMenor(array, leftIndex, rightIndex);
			// Array auxiliar para contabilizar o número de recorrências de cada número do array
			int[] auxArray = new int[maior - menor + 1];
			// Itera sobre o intervalo e registra no array auxiliar o número de recorrências de cada número
			for (int i = leftIndex; i <= rightIndex; i++) {
				auxArray[array[i] - menor] += 1;
			}

			// Acumulativa
			for (int j = 1; j < auxArray.length; j++) {
				auxArray[j] += auxArray[j-1];
			}

			// Array para guardar os elementos do intervalo ordenados
			int[] arrayOrdenado = new int[rightIndex - leftIndex + 1];
			// Adicionando os elementos do array principal no array anterior ordenadamente
			for (int k = rightIndex; k >= leftIndex; k--) {
				arrayOrdenado[auxArray[array[k] - menor] - 1] = array[k];
				auxArray[array[k] - menor] -= 1;
			}

			// Sobrescreve os valores do intervalo do array original pelos valores do array ordenado
			int auxIndex = 0;
			for (int n = leftIndex; n <= rightIndex; n++) {
				array[n] = arrayOrdenado[auxIndex++];
			}

		}
	}

	// Busca o maior elemento de um intervalo do array
	private int getMaior(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[leftIndex];
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i] > maior) {
				maior = array[i];
			}
		}
		return maior;
	}

	// Busca o menor elemento de um intervalo do array
	private int getMenor(Integer[] array, int leftIndex, int rightIndex) {
		int menor = array[leftIndex];
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i] < menor) {
				menor = array[i];
			}
		}
		return menor;
	}

	// Método auxiliar para validar os parâmetros do array e do intervalo
	private boolean validaParametros(Integer[] array, int leftIndex, int rightIndex) {
		boolean resp = false;
		if (array != null && array.length > 0 && rightIndex < array.length && leftIndex >= 0 && rightIndex >= 0 && leftIndex <= rightIndex) {
			resp = true;
		}
		return resp;
	}

}
