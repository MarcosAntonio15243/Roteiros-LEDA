package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
		
	}

	// Sobrescrevendo o método insert(T element) para se adaptar ao previous
	@Override
	public void insert(T element) {
		if (element != null) {
			if (super.isEmpty()) {
				this.insertFirst(element);
			} else if (super.getNext().isEmpty()) {
				// Criando o novo node
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>();
				newNode.setData(element);
				newNode.setNext(new RecursiveDoubleLinkedListImpl<T>());
				// Copiando os dados do node atual (e setando o novo node como next do atual)
				RecursiveDoubleLinkedListImpl<T> currentNode = new RecursiveDoubleLinkedListImpl<T>();
				currentNode.setData(super.getData());
				currentNode.setPrevious(this.previous);
				currentNode.setNext(newNode);
				// Setando o node atual como previous do novo node)
				newNode.setPrevious(currentNode);
				// Setando o novo node como next
				super.setNext(newNode);
			} else {
				super.getNext().insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (super.isEmpty()) {
				super.setNext(new RecursiveDoubleLinkedListImpl<T>());
			} else {
				// Copiando o head (com o data e o next)
				RecursiveDoubleLinkedListImpl<T> head = new RecursiveDoubleLinkedListImpl<T>();
				head.setData(super.getData());
				head.setNext((RecursiveDoubleLinkedListImpl<T>) super.getNext());
				// Criando o novo head
				RecursiveDoubleLinkedListImpl<T> newHead = new RecursiveDoubleLinkedListImpl<T>();
				newHead.setData(element);
				newHead.setNext(head);
				newHead.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
				// Setando o novo head como previous do antigo head
				head.setPrevious(newHead);
				// Setando o antigo head como next do novo head
				super.setNext(head);		
			}
			super.setData(element);
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
		}
	}

	@Override
	public void removeFirst() {
		if (!super.isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> newHead = (RecursiveDoubleLinkedListImpl<T>) super.getNext();
			newHead.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			super.setData(newHead.getData());
			super.setNext(newHead.getNext());
		}
	}

	@Override
	public void removeLast() {
		if (!super.isEmpty()) {
			if (!super.getNext().isEmpty()) {
				((RecursiveDoubleLinkedListImpl<T>) super.getNext()).removeLast();
			} else {
				RecursiveDoubleLinkedListImpl<T> newLast = this.getPrevious();
				newLast.setNext(super.getNext());
				super.setData(super.getNext().getData());
				super.setNext(super.getNext());
			}
		}
	}

	// Sobrescrevendo o método remove para se adaptar ao previous
	@Override
	public void remove(T element) {
		if (element != null && !super.isEmpty()) {
			if (super.getData().equals(element)) {
				RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) super.getNext();
				super.setData(next.getData());
				next.setPrevious(this.getPrevious());
				super.setNext((RecursiveDoubleLinkedListImpl<T>) next.getNext());
			} else {
				((RecursiveDoubleLinkedListImpl<T>) super.getNext()).remove(element);
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
