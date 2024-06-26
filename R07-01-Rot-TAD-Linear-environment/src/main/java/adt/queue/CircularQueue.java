package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		if (element != null) {
			if (this.isEmpty()) {
				this.head = (this.head + 1) % this.array.length;
			}
			this.tail = (this.tail + 1) % this.array.length;
			this.array[this.tail] = element;
			this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T element = this.head();
		if (this.elements == 1) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.head = (this.head + 1) % this.array.length;
		}
		this.elements--;
		return element;
	}

	@Override
	public T head() {
		T element = null;
		if (!this.isEmpty()) {
			element = this.array[this.head];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.head == -1 && this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.array.length == 0 || (this.tail + 1) % this.array.length == this.head;
	}

}
