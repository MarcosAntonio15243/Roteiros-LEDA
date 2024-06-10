package vetor;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator<T> comparadorMaximo;
	private Comparator<T> comparadorMinimo;

	@SuppressWarnings("unchecked")
	public Vetor(int tamanho) {
		super();
		this.arrayInterno = (T[]) new Object[tamanho];
		this.tamanho = tamanho;
		this.indice = 0;
		this.comparadorMaximo = null;
		this.comparadorMinimo = null;
	}

	public void setComparadorMaximo(Comparator<T> comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator<T> comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		if (!this.isCheio()) {
			this.arrayInterno[indice++] = o;
		}
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		T elementoRemovido = null;
		boolean removeu = false;
		int i = 0;
		while (i < this.tamanho && !removeu) {
			if (o.equals(this.arrayInterno[i])) {
				elementoRemovido = this.arrayInterno[i];
				this.arrayInterno[i] = this.arrayInterno[--this.indice];
				this.arrayInterno[this.indice] = null;
				removeu = true;
			}
			i += 1;
		}
		return elementoRemovido;
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		T elementoResp = null;
		boolean achou = false;
		int i = 0;
		while (i < this.tamanho && !achou) {
			if (o.equals(this.arrayInterno[i])) {
				elementoResp = this.arrayInterno[i];
				achou = true;
			}
			i += 1;
		}
		return elementoResp;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return this.indice == 0;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return this.indice == this.tamanho;
	}
	
	// Retorna o elemento máximo do vetor
	public T maximo() {
		T elementoMaximo = null;
		if (!this.isVazio() && this.comparadorMaximo != null) {
			elementoMaximo = this.arrayInterno[0];
			if (this.tamanho > 1) {
				for (int i = 1; i < this.tamanho; i++) {
					if (this.arrayInterno[i] == null) {
						return elementoMaximo;
					} else if (this.comparadorMaximo.compare(elementoMaximo, this.arrayInterno[i]) < 0) {
						elementoMaximo = this.arrayInterno[i];
					}
				}

			}
		}
		return elementoMaximo;
	}
	
	// Retorna o elemento mínimo do vetor
	public T minimo() {
		T elementoMinimo = null;
		if (!this.isVazio() && this.comparadorMinimo != null) {
			elementoMinimo = this.arrayInterno[0];
			if (this.tamanho > 1) {
				for (int i = 1; i < this.tamanho; i++) {
					if (this.arrayInterno[i] == null) {
						return elementoMinimo;
					} else if (this.comparadorMinimo.compare(elementoMinimo, this.arrayInterno[i]) < 0) {
						elementoMinimo = this.arrayInterno[i];
					}
				}

			}
		}
		return elementoMinimo;
	}

}

class ElementoMaximo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return (int) (aluno1.getMedia() - aluno2.getMedia());
	}
	
}

class ElementoMinimo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return (int) (aluno2.getMedia() - aluno1.getMedia());
	}
	
}