package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super.setHead(new DoubleLinkedListNode<T>());
		this.last = new DoubleLinkedListNode<T>();
	}

	// Sobrescrevendo o insert(T element) para se adaptar ao previous
	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNodeLast = new DoubleLinkedListNode<T>();
			newNodeLast.setData(element);
			newNodeLast.setNext(new DoubleLinkedListNode<T>());
			newNodeLast.setPrevious(this.last);
			this.last.setNext(newNodeLast);
			if (this.last.isNIL()) {
				super.setHead(newNodeLast);
			}
			this.last = newNodeLast;
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNodeHead = new DoubleLinkedListNode<T>();
			newNodeHead.setData(element);
			DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) super.getHead();
			newNodeHead.setNext(head);
			newNodeHead.setPrevious(new DoubleLinkedListNode<T>());
			head.setPrevious(newNodeHead);
			if (head.isNIL()) {
				this.last = newNodeHead;
			}
			super.setHead(newNodeHead);
		}
	}

	// Sobrescrevendo o método remove(T element) para se adaptar ao previous
	@Override
	public void remove(T element) {
		if (element != null && !super.isEmpty()) {
			DoubleLinkedListNode<T> currentNode = (DoubleLinkedListNode<T>) super.getHead();
			if (element.equals(currentNode.getData())) {
				removeFirst();
			} else if (element.equals(this.last.getData())) {
				removeLast();
			} else {
				currentNode = (DoubleLinkedListNode<T>) currentNode.getNext();
				boolean foundElement = false;
				while (!currentNode.isNIL() && !foundElement) {
					if (element.equals(currentNode.getData())) {
						// Caso encontre o node atual seja o elemento procurado:
						// Pega o previous do node atual e seta seu next como o next do node atual
						currentNode.getPrevious().setNext((DoubleLinkedListNode<T>) currentNode.getNext());
						// Pega o next do node atual e seta o seu previous como sendo o previous do node atual
						((DoubleLinkedListNode<T>) currentNode.getNext()).setPrevious(currentNode.getPrevious());
						// Deixa vazio o next do node atual
						currentNode.setNext(new DoubleLinkedListNode<T>());
						// Deixa vazio o previous do node atual
						currentNode.setPrevious(new DoubleLinkedListNode<T>());
						// Encontrou o elemento
						foundElement = true;
					} else {
						// Caso não encontrou o elemento, anda para o próximo node
						currentNode = (DoubleLinkedListNode<T>) currentNode.getNext();
					}
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!super.getHead().isNIL()) {
			DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) super.getHead().getNext();
			if (newHead.isNIL()) {
				this.last = newHead;
			} else {
				newHead.setPrevious(new DoubleLinkedListNode<T>());
			}
			super.setHead(newHead);
		}
	}

	@Override
	public void removeLast() {
		if (!this.last.isNIL()) {
			DoubleLinkedListNode<T> newLast = this.last.getPrevious();
			if (newLast.isNIL()) {
				super.setHead(newLast);
			} else {
				newLast.setNext(new DoubleLinkedListNode<T>());
			}
			this.last = newLast;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
