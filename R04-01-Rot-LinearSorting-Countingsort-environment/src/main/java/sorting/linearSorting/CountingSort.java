package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		// Valida os parâmetros
		if (validaParametros(array, leftIndex, rightIndex)) {

			// Buscando o maior elemento do intervalo
			int maior = getMaior(array, leftIndex, rightIndex);
			// Criando o array auxiliar para guardar o número de ocorrências de cada valor do array principal
			int[] auxArray = new int[maior+1];
			// Itera sobre o intervalo e registra no array auxiliar o número de recorrências de cada número
			for (int i = leftIndex; i <= rightIndex; i++) {
				auxArray[array[i]] += 1; 
			}

			// Acumulativa
			for (int j = 1; j < auxArray.length; j++) {
				auxArray[j] += auxArray[j-1];
			}

			// Array para guardar os elementos do intervalo ordenados
			int[] arrayOrdenado = new int[rightIndex - leftIndex + 1];
			// Adicionando os elementos do array principal no array anterior ordenadamente
			for (int k = rightIndex; k >= leftIndex; k--) {
				arrayOrdenado[auxArray[array[k]]-1] = array[k];
				auxArray[array[k]] -= 1;
			}

			// Sobrescreve os valores do intervalo do array original pelos valores do array ordenado
			int auxIndex = 0;
			for (int n = leftIndex; n <= rightIndex; n++) {
				array[n] = arrayOrdenado[auxIndex++];
			}

		}
	}

	// Busca o maior elemento do um intervalo do array
	private int getMaior(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[leftIndex];
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i] > maior) {
				maior = array[i];
			}
		}
		return maior;
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
