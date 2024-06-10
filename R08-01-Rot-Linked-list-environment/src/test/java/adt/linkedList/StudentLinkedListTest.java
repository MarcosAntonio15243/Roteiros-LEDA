package adt.linkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentLinkedListTest {

	protected LinkedList<Integer> lista1;
	protected LinkedList<Integer> lista2;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new SingleLinkedListImpl<Integer>();
		lista2 = new SingleLinkedListImpl<Integer>();
	}

	// TESTES PARA SINGLE_LINKED_LIST (ITERATIVA)

	// Inserir elemento em lista vazia
	@Test
	public void testInsertListaVazia() {
		//assertEquals(null, ((SingleLinkedListImpl<Integer>) lista2).getHead().getData());
		assertEquals(0, lista2.size());
		lista2.insert(4);
		//assertEquals(new Integer(4), ((SingleLinkedListImpl<Integer>) lista2).getHead().getData());
		assertEquals(1, lista2.size());
	}

	// Inserir elemento em lista com elementos
	@Test
	public void testInsertListaComElementos() {
		//assertEquals(new Integer(3), ((SingleLinkedListImpl<Integer>) lista1).getHead().getData());
		assertEquals(3, lista1.size());
		lista1.insert(4);
		assertEquals(4, lista1.size());
	}

	// Inserir elemento null em lista vazia (não insere)
	@Test
	public void testInsertNullListaVazia() {
		//assertEquals(null, ((SingleLinkedListImpl<Integer>) lista2).getHead().getData());
		assertEquals(0, lista2.size());
		lista2.insert(null);
		//assertEquals(null, ((SingleLinkedListImpl<Integer>) lista2).getHead().getData());
		assertEquals(0, lista2.size());
	}

	// Inserir elemento null em lista com elementos (não insere)
	@Test
	public void testInsertNullListaComElementos() {
		//assertEquals(new Integer(3), ((SingleLinkedListImpl<Integer>) lista1).getHead().getData());
		assertEquals(3, lista1.size());
		lista1.insert(null);
		//assertEquals(new Integer(3), ((SingleLinkedListImpl<Integer>) lista1).getHead().getData());
		assertEquals(3, lista1.size());
	}

	// Buscar elemento existente na lista com elementos
	@Test
	public void testGetElementoExistente() {
		assertTrue(lista1.search(2) == 2);
		assertTrue(lista1.search(3) == 3);
		assertTrue(lista1.search(1) == 1);
	}

	// Buscar elemento em lista vazia (retorna null)
	@Test
	public void testGetElementoListaVazia() {
		assertEquals(null, lista2.search(2));
		assertEquals(null, lista2.search(3));
		assertEquals(null, lista2.search(1));
	}

	// Buscar elemento inexistente na lista  (retorna null)
	@Test
	public void testGetElementoInexistente() {
		assertEquals(null, lista1.search(0));
		assertEquals(null, lista1.search(4));
	}

	// Buscar elemento null na lista (retorna null)
	@Test
	public void testGetElementoNull() {
		assertEquals(null, lista1.search(null));
		assertEquals(null, lista2.search(null));
	}

	// Remover elemento existente em uma lista com mais de um elemento
	@Test
	public void testRemoverElemento() {
		assertTrue(lista1.search(2) == 2);
		lista1.remove(2);
		assertTrue(lista1.search(2) == null);
	}

	// Remover elemento existente em uma lista com um único elemento
	@Test
	public void testRemoverElementoListaUnitaria() {
		LinkedList<Integer> listaUnitaria = new SingleLinkedListImpl<Integer>();
		assertTrue(listaUnitaria.search(2) == null);
		listaUnitaria.insert(2);
		assertTrue(listaUnitaria.search(2) == 2);
		listaUnitaria.remove(2);
		assertTrue(listaUnitaria.search(2) == null);
	}

	// Remover elemento inexistente
	@Test
	public void testRemoveElementoInexistente() {
		assertEquals(null, lista1.search(0));
		lista1.remove(0);
		assertEquals(null, lista1.search(0));
	}




	@Test
	public void testIsEmpty() {
		Assert.assertFalse(lista1.isEmpty());
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
		Assert.assertEquals(0, lista2.size());
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(2 == lista1.search(2));
		Assert.assertNull(lista1.search(4));
		Assert.assertFalse(3 == lista1.search(2));
	}

	@Test
	public void testInsert() {
		Assert.assertEquals(3, lista1.size());
		lista1.insert(5);
		lista1.insert(7);
		Assert.assertEquals(5, lista1.size());

		Assert.assertEquals(0, lista2.size());
		lista2.insert(4);
		lista2.insert(7);
		Assert.assertEquals(2, lista2.size());
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());

	}

	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
	}

}