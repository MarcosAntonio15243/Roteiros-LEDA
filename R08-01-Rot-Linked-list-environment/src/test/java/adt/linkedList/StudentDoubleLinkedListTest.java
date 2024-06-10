package adt.linkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	private DoubleLinkedList<Integer> recursiveLista1;
	private DoubleLinkedList<Integer> recursiveLista2;
	private DoubleLinkedList<Integer> recursiveLista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);

		// Lista recursiva com 3 elementos.
		recursiveLista1.insert(3);
		recursiveLista1.insert(2);
		recursiveLista1.insert(1);

		// Lista recursiva com 1 elemento.
		recursiveLista3.insert(1);


	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new DoubleLinkedListImpl<>();
		lista2 = new DoubleLinkedListImpl<Integer>();
		lista3 = new DoubleLinkedListImpl<Integer>();

		recursiveLista1 = new RecursiveDoubleLinkedListImpl<Integer>();
		recursiveLista2 = new RecursiveDoubleLinkedListImpl<Integer>();
		recursiveLista3 = new RecursiveDoubleLinkedListImpl<Integer>();
	}

	// Métodos de DoubleLinkedList (iterativa)

	@Test
	public void testGetHeadAndLastListaVazia() {
		assertEquals(null, ((SingleLinkedListImpl<Integer>) lista2).getHead().getData());
		assertEquals(null, ((DoubleLinkedListImpl<Integer>) lista2).getLast().getData());
	}

	@Test
	public void testGetHeadAndLastListaUmElemento() {
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == 1);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getLast().getData() == 1);
	}

	@Test
	public void testGetHeadAndLastListaMaisDeUmElemento() {
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getHead().getData() == 3);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getLast().getData() == 1);
	}

	@Test
	public void insereListaVazia() {
		assertEquals(0, lista2.size());
		assertTrue(lista2.isEmpty());
		lista2.insert(2);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista2).getHead().getData() == 2);
		assertEquals(1, lista2.size());
		assertFalse(lista2.isEmpty());
	}

	@Test
	public void insereRemoveListaVazia() {
		lista2.insert(2);
		lista2.remove(2);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista2).getHead().getData() == null);
	}

	@Test
	public void inserePrimeiroListaVazia() {
		((DoubleLinkedList<Integer>) lista2).insertFirst(2);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista2).getHead().getData() == 2);
	}

	@Test
	public void insereNormalRemovePrimeiroListaVazia() {
		lista2.insert(2);
		((DoubleLinkedList<Integer>) lista2).removeFirst();
		assertTrue(((DoubleLinkedListImpl<Integer>) lista2).getHead().getData() == null);
	}

	@Test
	public void insereRemovePrimeiroListaVazia() {
		((DoubleLinkedList<Integer>) lista2).insertFirst(2);
		assertEquals(1, lista2.size());
		assertFalse(lista2.isEmpty());

		((DoubleLinkedList<Integer>) lista2).removeFirst();
		assertEquals(0, lista2.size());
		assertTrue(lista2.isEmpty());
	}


	@Test
	public void testRemoveFirstUnicoElemento() {
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == 1);
		((DoubleLinkedList<Integer>) lista3).removeFirst();
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == null);
	}

	@Test
	public void testRemoveLastUnicoElemento() {
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == 1);
		((DoubleLinkedList<Integer>) lista3).removeLast();
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == null);
	}

	@Test
	public void testRemoveComElementos() {
		lista1.remove(2);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getHead().getData() == 3);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getLast().getData() == 1);
	}

	@Test
	public void testRemoveFirstComElementos() {
		((DoubleLinkedListImpl<Integer>) lista1).removeFirst();
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getHead().getData() == 2);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getLast().getData() == 1);
	}

	@Test
	public void testRemoveLastComElementos() {
		((DoubleLinkedListImpl<Integer>) lista1).removeLast();
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getHead().getData() == 3);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getLast().getData() == 2);
	}

	@Test
	public void testRemoveMudandoHead() {
		((DoubleLinkedListImpl<Integer>) lista3).insert(4);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == 1);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getLast().getData() == 4);
		((DoubleLinkedListImpl<Integer>) lista3).removeFirst();
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == 4);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getLast().getData() == 4);
	}


	@Test
	public void testAdicionaMudandoLast() {
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == 1);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getLast().getData() == 1);
		lista3.insert(2);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getHead().getData() == 1);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista3).getLast().getData() == 2);
	}


	@Test
	public void testRemoveDouble() {
		assertTrue(lista1.search(2) == 2);
		lista1.remove(2);
		assertTrue(lista1.search(2) == null);
	}

	@Test
	public void testRemoveFirstUnderflow() {
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getHead().getData() == 3);
		assertTrue(lista1.search(3) == 3);
		((DoubleLinkedListImpl<Integer>) lista1).remove(3);
		assertTrue(lista1.search(3) == null);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getHead().getData() == 2);
	}

	@Test
	public void testRemoveLastOverride() {
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getLast().getData() == 1);
		assertTrue(lista1.search(1) == 1);
		((DoubleLinkedListImpl<Integer>) lista1).remove(1);
		assertTrue(lista1.search(1) == null);
		assertTrue(((DoubleLinkedListImpl<Integer>) lista1).getLast().getData() == 2);
	}



	// Testes para DoubleLinkedList (Recursivo)

	@Test
	public void testInsereListaVaziaRecursivo() {
		assertEquals(0, recursiveLista2.size());
		assertTrue(recursiveLista2.isEmpty());
		recursiveLista2.insert(2);
		assertEquals(1, recursiveLista2.size());
		assertFalse(recursiveLista2.isEmpty());
	}

	@Test
	public void testInsereRemoveListaVaziaRecursivo() {
		recursiveLista2.insert(2);
		recursiveLista2.remove(2);
		assertEquals(0, recursiveLista2.size());
		assertTrue(recursiveLista2.isEmpty());
	}

	@Test
	public void testInserePrimeiroListaVaziaRecursivo() {
		assertTrue(recursiveLista2.search(4) == null);
		recursiveLista2.insertFirst(4);
		assertTrue(recursiveLista2.search(4) == 4);
	}

	@Test
	public void testInsereNormalRemovePrimeiroListaVaziaRecursivo() {
		recursiveLista2.insert(4);
		assertTrue(recursiveLista2.search(4) == 4);
		recursiveLista2.removeFirst();
		assertTrue(recursiveLista2.search(4) == null);
	}

	@Test
	public void testInsereRemovePrimeiroListaVaziaRecursivo() {
		recursiveLista2.insertFirst(4);
		assertTrue(recursiveLista2.search(4) == 4);
		recursiveLista2.removeFirst();
		assertTrue(recursiveLista2.search(4) == null);
	}

	@Test
	public void testRemoveFirstUnicoElementoRecursivo() {
		assertTrue(recursiveLista3.search(1) == 1);
		recursiveLista3.removeFirst();
		assertTrue(recursiveLista3.search(1) == null);
	}

	@Test
	public void testRemoveLastUnicoElementoRecursivo() {
		assertTrue(recursiveLista3.search(1) == 1);
		recursiveLista3.removeLast();
		assertTrue(recursiveLista3.search(1) == null);
	}

	@Test
	public void testRemoveComElementosRecursivo() {
		assertTrue(recursiveLista1.search(2) == 2);
		recursiveLista1.remove(2);
		assertTrue(recursiveLista1.search(2) == null);
	}

	@Test
	public void testRemoveFirstComElementosRecursivo() {
		assertTrue(recursiveLista1.search(3) == 3);
		recursiveLista1.removeFirst();
		assertTrue(recursiveLista1.search(3) == null);
	}

	@Test
	public void testRemoveLastComElementosRecursivo() {
		assertTrue(recursiveLista1.search(1) == 1);
		recursiveLista1.removeLast();
		assertTrue(recursiveLista1.search(1) == null);
	}
	
	@Test
	public void testToArrayRecursivo() {
		assertArrayEquals(new Integer[]{ 3, 2, 1 }, recursiveLista1.toArray());
		((RecursiveDoubleLinkedListImpl<Integer>) recursiveLista1).remove(2);
		assertArrayEquals(new Integer[]{ 3, 1 }, recursiveLista1.toArray());
		((RecursiveDoubleLinkedListImpl<Integer>) recursiveLista1).remove(3);
		assertArrayEquals(new Integer[]{ 1 }, recursiveLista1.toArray());
		((RecursiveDoubleLinkedListImpl<Integer>) recursiveLista1).remove(1);
		assertArrayEquals(new Integer[]{}, recursiveLista1.toArray());
	}

	@Test
	public void testRemoveFirstListaVaziaRecursivo() {
		assertTrue(recursiveLista2.isEmpty());
		recursiveLista2.removeFirst();
		assertTrue(recursiveLista2.isEmpty());
	}

	@Test
	public void testRemoveLastListaVaziaRecursivo() {
		assertTrue(recursiveLista2.isEmpty());
		recursiveLista2.removeLast();
		assertTrue(recursiveLista2.isEmpty());
	}

	@Test
	public void testRemoveElementoListaVaziaRecursivo() {
		assertTrue(recursiveLista2.isEmpty());
		recursiveLista2.remove(3);
		assertTrue(recursiveLista2.isEmpty());
	}

	@Test
	public void testRemoveElementoNullListaVaziaRecursivo() {
		assertTrue(recursiveLista2.isEmpty());
		recursiveLista2.remove(null);
		assertTrue(recursiveLista2.isEmpty());
	}



	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}
}