package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		if (!this.isLeaf(position) && position >= 0 && position <= this.index) {
			int indexMax = this.getMaxChild(position, left(position), right(position));
			if (indexMax != position) {
				Util.swap(this.heap, position, indexMax);
				heapify(indexMax);
			}
		}
	}
	private int getMaxChild(int indexElement, int left, int right) {
		int indexMax = indexElement;
		if (validaIndex(left) && comparator.compare(heap[left], heap[indexElement]) > 0) {
			if (validaIndex(right) && comparator.compare(heap[right], heap[left]) > 0) {
				indexMax = right;
			} else {
				indexMax = left;
			}
		} else {
			if (validaIndex(right) && comparator.compare(heap[right], heap[indexElement]) > 0) {
				indexMax = right;
			}
		}
		return indexMax;
	}
	private boolean validaIndex(int index) {
		return index >= 0 && index <= this.index;
	}
	private boolean isLeaf(int indexElement) {
		return indexElement > this.parent(this.index) && indexElement <= this.index;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
			if (index == heap.length - 1) {
				heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
			}
			// /////////////////////////////////////////////////////////////////
			this.heap[++this.index] = element;
			int indexElement = this.index;
			while (indexElement > 0 && this.comparator.compare(element, this.heap[parent(indexElement)]) > 0) {
				Util.swap(this.heap, parent(indexElement), indexElement);
				indexElement = parent(indexElement);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (array != null) {
			this.heap = array;
			this.index = array.length - 1;
			for (int i = this.parent(this.index); i >= 0; i--) {
				this.heapify(i);
			}
		}
	}

	@Override
	public T extractRootElement() {
		T root = null;
		if (!this.isEmpty()) {
			root = this.heap[0];
			Util.swap(this.heap, 0, this.index);
			this.index--;
			heapify(0);
		}
		return root;
	}

	@Override
	public T rootElement() {
		T rootElement = null;
		if (!this.isEmpty()) {
			rootElement = this.heap[0];
		}
		return rootElement;
	}

	public static void main(String[] args) {
		
	}

	@Override
	public T[] heapsort(T[] array) {
		T[] orderedArray = array;
		if (array != null && array.length > 0) {
			// Guardando dados originais da heap
			T[] oldArray = this.heap;
			int indexOldArray = this.index;
			Comparator<T> oldComparator = this.getComparator();
			// Setando um novo comparador orignial do tipo T para realizar a busca ordenada
			this.setComparator(new Comparator<T>() {
				@Override
				public int compare(T arg0, T arg1) {
					return arg0.compareTo(arg1);
				}
			});
			// Construindo a heap com o array original
			this.buildHeap(array);
			// Esvaziando a heap e ordenando o array
			for (int i = array.length-1; i >= 0; i--) {
				this.extractRootElement();
			}
			// Trazendo os dados originais da heap de volta
			this.heap = oldArray;
			this.index = indexOldArray;
			this.setComparator(oldComparator);
		}
		return orderedArray;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
