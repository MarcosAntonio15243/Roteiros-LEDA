package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		try {
			if (element != null) {
				this.stack1.push(element);
			}
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T element = null;
		try {
			// Transfere os elementos de Stack1 para Stack2
			while (!this.stack1.isEmpty()) {
				this.stack2.push(this.stack1.pop());
			}
			// Remove o último elemento adicionado em Stack2 (head de Stack1)
			element = this.stack2.pop();
			// Transfere os demais elementos de volta a Stack1
			while (!this.stack2.isEmpty()) {
				this.stack1.push(this.stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			throw new QueueUnderflowException();
		}
		return element;
	}

	@Override
	public T head() {
		T element = null;
		try {
			while (!this.stack1.isEmpty()) {
				this.stack2.push(this.stack1.pop());
			}
			element = this.stack2.pop();
			this.stack1.push(element);
			while (!this.stack2.isEmpty()) {
				this.stack1.push(this.stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException ignored) {
			// Este bloco de código não executa nada pois a função head() não deve lançar exceções
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
