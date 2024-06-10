package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueDoubleLinkedListImpl<Integer>(4);
		queue2 = new QueueDoubleLinkedListImpl<Integer>(2);
		queue3 = new QueueDoubleLinkedListImpl<Integer>(0);;
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertTrue(queue1.head() == 1);
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		assertEquals(new Integer(1), queue3.dequeue()); // vai depender do
														// tamanho que a fial
														// foi iniciada!!!
	}


	// TESTES EXTRAS

	// Auxiliar ao anterior, teste enqueue para lista vazia
	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErroListaVazia() throws QueueOverflowException {
		queue3.enqueue(4);
	}

	private Queue<Integer> queueExtra = new QueueDoubleLinkedListImpl<Integer>(3);

	// Adicionar elemento em lista vazia
	@Test
	public void testEnqueueListaVazia() throws QueueOverflowException {
		assertEquals(null, queueExtra.head());
		queueExtra.enqueue(4);
		assertEquals(new Integer(4), queueExtra.head());
	}

	// Adicionar elemento em lista com elementos (Não altera o head)
	@Test
	public void testEnqueueListaComElementos() throws QueueOverflowException {
		assertEquals(new Integer(1), queue1.head());
		queue1.enqueue(4);
		assertEquals(new Integer(1), queue1.head());
	}

	// Remover um elemento da lista
	@Test
	public void testDequeueListaComElementos() throws QueueUnderflowException {
		assertTrue(queue2.isFull());
		queue2.dequeue();
		assertFalse(queue2.isFull());
	}

	// Remover um elemento da lista unitária
	@Test
	public void testDequeueListaUnitaria() throws QueueUnderflowException, QueueOverflowException {
		assertTrue(queueExtra.isEmpty());
		assertEquals(null, queueExtra.head());

		queueExtra.enqueue(4);
		assertFalse(queueExtra.isEmpty());
		assertEquals(new Integer(4), queueExtra.head());

		queueExtra.dequeue();
		assertTrue(queueExtra.isEmpty());
		assertEquals(null, queueExtra.head());
	}

	

}