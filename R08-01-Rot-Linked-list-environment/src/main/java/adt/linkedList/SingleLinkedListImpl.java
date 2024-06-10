package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> currentNode = this.head;
		while (!currentNode.isNIL()) {
			size++;
			currentNode = currentNode.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> currentNode = this.head;
		boolean foundElement = false;
		while (!currentNode.isNIL() && !foundElement) {
			if (currentNode.getData().equals(element)) {
				foundElement = true;
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return currentNode.getData();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> currentNode = this.head;
			while (!currentNode.isNIL()) {
				currentNode = currentNode.getNext();
			}
			currentNode.setData(element);
			currentNode.setNext(new SingleLinkedListNode<T>());
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty() && element != null) {
			if (!this.head.isNIL() && this.head.getData().equals(element)) {
				this.head = this.head.getNext();
			} else {
				SingleLinkedListNode<T> currentNode = this.head;
				boolean removed = false;
				while(!currentNode.getNext().isNIL() && !removed) {
					if (currentNode.getNext().getData().equals(element)) {
						currentNode.setNext(currentNode.getNext().getNext());
						removed = true;
					} else {
						currentNode = currentNode.getNext();
					}	
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		int index = 0;
		SingleLinkedListNode<T> currentNode = this.head;
		while (!currentNode.isNIL()) {
			array[index++] = currentNode.getData();
			currentNode = currentNode.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
