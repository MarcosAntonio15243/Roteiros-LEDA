package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() { }

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int size = 0;
		if (!this.isEmpty()) {
			size = 1 + this.next.size();
		}
		return size;
	}

	@Override
	public T search(T element) {
		T elementResult = null;
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				elementResult = this.data;
			} else {
				elementResult = this.next.search(element);
			}
			
		}
		return elementResult;
	}
	

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<T>();
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.data.equals(element)) {
				this.data = this.next.getData();
				this.next = this.next.getNext();
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		RecursiveDoubleLinkedListImpl<T> currentNode = new RecursiveDoubleLinkedListImpl<T>();
		currentNode.setData(this.data);
		currentNode.setNext(this.next);
		this.preencheElementos(array, currentNode, 0);
		return array;
	}

	/**
	 * Método para preencher os elementos do array do método toArray()
	 * 
	 * @param array O array a ser preenchido
	 * @param currentNode O primeiro nó da SingleLinkedList
	 * @param index O índice do array a ser adicionado o nó
	 */
	private void preencheElementos(T[] array, RecursiveSingleLinkedListImpl<T> currentNode, int index) {
		if (currentNode.getData() != null) {
			array[index] = currentNode.getData();
			preencheElementos(array, currentNode.getNext(), index + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
