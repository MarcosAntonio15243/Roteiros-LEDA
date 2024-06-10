package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class TestesR07 {
    public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException, StackOverflowException, StackUnderflowException {

        // TESTES PARA CIRCULAR_QUEUE

        CircularQueue<Integer> circularQueue = new CircularQueue<>(4);
        System.out.println("Rodando");
		assertTrue(circularQueue.isEmpty());
		assertFalse(circularQueue.isFull());
		assertEquals(null, circularQueue.head());

		circularQueue.enqueue(null);
		assertTrue(circularQueue.isEmpty());

		circularQueue.enqueue(3);
		assertFalse(circularQueue.isEmpty());
		assertFalse(circularQueue.isFull());
		assertEquals(new Integer(3), circularQueue.head());

		circularQueue.enqueue(4);
		circularQueue.enqueue(-3);
		circularQueue.enqueue(1);
		assertEquals(new Integer(3), circularQueue.head());

		try {
			circularQueue.enqueue(7);
		} catch (QueueOverflowException e) {
			System.out.println("Erro1: " + e.getMessage());
		}
		try {
			circularQueue.enqueue(null);
		} catch (QueueOverflowException e) {
			System.out.println("Erro2: " + e.getMessage());
		}
		assertEquals(new Integer(3), circularQueue.head());
		assertFalse(circularQueue.isEmpty());
		assertTrue(circularQueue.isFull());

		assertEquals(new Integer(3), circularQueue.dequeue());
		assertEquals(new Integer(4), circularQueue.head());

		circularQueue.enqueue(7);
		assertFalse(circularQueue.isEmpty());
		assertTrue(circularQueue.isFull());

		assertEquals(new Integer(4), circularQueue.dequeue());
		assertEquals(new Integer(-3), circularQueue.head());
		assertFalse(circularQueue.isFull());

		assertEquals(new Integer(-3), circularQueue.dequeue());
		assertEquals(new Integer(1), circularQueue.head());

		assertEquals(new Integer(1), circularQueue.dequeue());
		assertEquals(new Integer(7), circularQueue.head());

		assertEquals(new Integer(7), circularQueue.dequeue());
		assertEquals(null, circularQueue.head());
		assertTrue(circularQueue.isEmpty());
		assertFalse(circularQueue.isFull());

		try {
			circularQueue.dequeue();
		} catch (QueueUnderflowException e) {
			System.out.println("Erro3: " + e.getMessage());
		}

		circularQueue.enqueue(8);
		assertFalse(circularQueue.isEmpty());
		assertFalse(circularQueue.isFull());
		assertEquals(new Integer(8), circularQueue.head());

		// TESTES PARA FILA CIRCULAR DE TAMANHO 0
		CircularQueue<Integer> circularQueueVazia = new CircularQueue<>(0);
		assertTrue(circularQueueVazia.isEmpty());
		assertTrue(circularQueueVazia.isFull());
		assertEquals(null, circularQueueVazia.head());
		try {
			circularQueueVazia.enqueue(1);
		} catch (QueueOverflowException e) {
			System.out.println("Erro4: " + e.getMessage());
		}
		try {
			circularQueueVazia.enqueue(null);
		} catch (QueueOverflowException e) {
			System.out.println("Erro5: " + e.getMessage());
		}
		try {
			circularQueueVazia.dequeue();
		} catch (QueueUnderflowException e) {
			System.out.println("Erro6: " + e.getMessage());
		}

        
        // TESTES PARA QUEUE_IMPL

        // TESTES PARA UMA FILA QUALQUER
        QueueImpl<Integer> fila = new QueueImpl<>(3);
		System.out.println("Rodando");
		assertTrue(fila.isEmpty());
		assertFalse(fila.isFull());
		assertEquals(null, fila.head());

		fila.enqueue(null);
		assertTrue(fila.isEmpty());

		fila.enqueue(3);
		assertFalse(fila.isEmpty());
		assertFalse(fila.isFull());
		assertEquals(new Integer(3), fila.head());

		fila.enqueue(-4);
		assertEquals(new Integer(3), fila.head());
		fila.enqueue(30);
		assertEquals(new Integer(3), fila.head());
		assertFalse(fila.isEmpty());
		assertTrue(fila.isFull());

		try {
			fila.enqueue(43);
		} catch (QueueOverflowException e) {
			System.out.println("Erro1: " + e.getMessage());
		}

		assertEquals(new Integer(3), fila.dequeue());
		assertEquals(new Integer(-4), fila.head());
		assertFalse(fila.isEmpty());
		assertFalse(fila.isFull());		

		assertEquals(new Integer(-4), fila.dequeue());
		assertEquals(new Integer(30), fila.head());

		assertEquals(new Integer(30), fila.dequeue());
		assertEquals(null, fila.head());
		assertTrue(fila.isEmpty());
		assertFalse(fila.isFull());

		try {
			fila.dequeue();
		} catch (QueueUnderflowException e) {
			System.out.println("Erro2: " + e.getMessage());
		}

		// TESTES PARA FILA DE TAMANHO 0
		QueueImpl<Integer> filaVazia = new QueueImpl<>(0);
		System.out.println("Fila Vazia:");
		assertTrue(filaVazia.isEmpty());
		assertTrue(filaVazia.isFull());
		assertEquals(null, fila.head());
		try {
			filaVazia.enqueue(43);
		} catch (QueueOverflowException e) {
			System.out.println("Erro3: " + e.getMessage());
		}
		try {
			filaVazia.enqueue(null);
		} catch (QueueOverflowException e) {
			System.out.println("Erro4: " + e.getMessage());
		}
		try {
			filaVazia.dequeue();
		} catch (QueueUnderflowException e) {
			System.out.println("Erro5: " + e.getMessage());
		}
		assertTrue(filaVazia.isEmpty());
		assertTrue(filaVazia.isFull());
		assertEquals(null, fila.head());


        // TESTES PARA QUEUE_USING_STACK

        QueueUsingStack<Integer> queueUsingStack = new QueueUsingStack<>(4);
		System.out.println("Rodando");
		assertTrue(queueUsingStack.isEmpty());
		assertFalse(queueUsingStack.isFull());
		assertEquals(null, queueUsingStack.head());

		queueUsingStack.enqueue(null);
		assertTrue(queueUsingStack.isEmpty());

		queueUsingStack.enqueue(3);
		assertFalse(queueUsingStack.isEmpty());
		assertFalse(queueUsingStack.isFull());
		assertEquals(new Integer(3), queueUsingStack.head());

		queueUsingStack.enqueue(4);
		queueUsingStack.enqueue(-3);
		queueUsingStack.enqueue(1);
		assertEquals(new Integer(3), queueUsingStack.head());

		try {
			queueUsingStack.enqueue(7);
		} catch (QueueOverflowException e) {
			System.out.println("Erro1: " + e.getMessage());
		}
		try {
			queueUsingStack.enqueue(null);
		} catch (QueueOverflowException e) {
			System.out.println("Erro2: " + e.getMessage());
		}
		assertEquals(new Integer(3), queueUsingStack.head());
		assertFalse(queueUsingStack.isEmpty());
		assertTrue(queueUsingStack.isFull());

		assertEquals(new Integer(3), queueUsingStack.dequeue());
		assertEquals(new Integer(4), queueUsingStack.head());

		queueUsingStack.enqueue(7);
		assertFalse(queueUsingStack.isEmpty());
		assertTrue(queueUsingStack.isFull());

		assertEquals(new Integer(4), queueUsingStack.dequeue());
		assertEquals(new Integer(-3), queueUsingStack.head());
		assertFalse(queueUsingStack.isFull());

		assertEquals(new Integer(-3), queueUsingStack.dequeue());
		assertEquals(new Integer(1), queueUsingStack.head());

		assertEquals(new Integer(1), queueUsingStack.dequeue());
		assertEquals(new Integer(7), queueUsingStack.head());

		assertEquals(new Integer(7), queueUsingStack.dequeue());
		assertEquals(null, queueUsingStack.head());
		assertTrue(queueUsingStack.isEmpty());
		assertFalse(queueUsingStack.isFull());

		try {
			queueUsingStack.dequeue();
		} catch (QueueUnderflowException e) {
			System.out.println("Erro3: " + e.getMessage());
		}

		queueUsingStack.enqueue(8);
		assertFalse(queueUsingStack.isEmpty());
		assertFalse(queueUsingStack.isFull());
		assertEquals(new Integer(8), queueUsingStack.head());

		QueueUsingStack<Integer> queueUsingStackVazia = new QueueUsingStack<>(0);
		assertTrue(queueUsingStackVazia.isEmpty());
		assertTrue(queueUsingStackVazia.isFull());
		assertEquals(null, queueUsingStackVazia.head());
		try {
			queueUsingStackVazia.enqueue(1);
		} catch (QueueOverflowException e) {
			System.out.println("Erro4: " + e.getMessage());
		}
		try {
			queueUsingStackVazia.enqueue(null);
		} catch (QueueOverflowException e) {
			System.out.println("Erro5: " + e.getMessage());
		}
		try {
			queueUsingStackVazia.dequeue();
		} catch (QueueUnderflowException e) {
			System.out.println("Erro6: " + e.getMessage());
		}


        // TESTES PARA STACK_IMPL

        // TESTES DE UMA STACK QUALQUER
		StackImpl<Integer> stack = new StackImpl<>(4);
		System.out.println("Rodando");
		assertTrue(stack.isEmpty());
		assertFalse(stack.isFull());
		assertEquals(null, stack.top());

		stack.push(4);
		assertEquals(new Integer(4), stack.top());
		assertFalse(stack.isEmpty());
		assertFalse(stack.isFull());

		stack.push(2);
		assertEquals(new Integer(2), stack.top());

		stack.push(6);
		assertEquals(new Integer(6), stack.top());

		stack.push(1);
		assertEquals(new Integer(1), stack.top());
		assertFalse(stack.isEmpty());
		assertTrue(stack.isFull());

		try {
			stack.push(7);
		} catch (StackOverflowException e) {
			System.out.println("Erro1: " + e.getMessage());
		}
		try {
			stack.push(null);
		} catch (StackOverflowException e) {
			System.out.println("Erro2: " + e.getMessage());
		}

		assertEquals(new Integer(1), stack.pop());
		assertEquals(new Integer(6), stack.top());
		assertFalse(stack.isEmpty());
		assertFalse(stack.isFull());

		assertEquals(new Integer(6), stack.pop());
		assertEquals(new Integer(2), stack.top());

		assertEquals(new Integer(2), stack.pop());
		assertEquals(new Integer(4), stack.top());

		assertEquals(new Integer(4), stack.pop());
		assertEquals(null, stack.top());
		assertTrue(stack.isEmpty());
		assertFalse(stack.isFull());

		try {
			stack.pop();
		} catch (StackUnderflowException e) {
			System.out.println("Erro3: " + e.getMessage());
		}

		// TESTES PARA ADICIONAR ELEMENTOS NULOS EM UMA STACK
		System.out.println("Stack null:");
		StackImpl<Integer> stackAndNull = new StackImpl<>(2);
		assertTrue(stackAndNull.isEmpty());
		assertFalse(stackAndNull.isFull());
		stackAndNull.push(null);
		stackAndNull.push(null);
		assertTrue(stackAndNull.isEmpty());
		assertFalse(stackAndNull.isFull());

		// TESTES PARA UMA STACK DE TAMANHO 0
		StackImpl<Integer> stackVazia = new StackImpl<>(0);
		System.out.println("Stack vazia:");
		assertTrue(stackVazia.isEmpty());
		assertTrue(stackVazia.isFull());
		assertEquals(null, stackVazia.top());
		try {
			stackVazia.push(7);
		} catch (StackOverflowException e) {
			System.out.println("Erro4: " + e.getMessage());
		}
		try {
			stackVazia.push(null);
		} catch (StackOverflowException e) {
			System.out.println("Erro5: " + e.getMessage());
		}
		try {
			stackVazia.pop();
		} catch (StackUnderflowException e) {
			System.out.println("Erro6: " + e.getMessage());
		}
		assertTrue(stackVazia.isEmpty());
		assertTrue(stackVazia.isFull());
		assertEquals(null, stackVazia.top());

    }
}