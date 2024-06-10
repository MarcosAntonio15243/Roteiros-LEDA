package adt.hashtable.open;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.open.AbstractHashtableOpenAddress;
import adt.hashtable.open.HashtableOpenAddressQuadraticProbingImpl;
import adt.hashtable.open.HashtableElement;

public class StudentTestHashtableOpenAddressQuadraticProbing {
	protected AbstractHashtableOpenAddress<HashtableElement> table1;
	protected AbstractHashtableOpenAddress<HashtableElement> table2;
	// EXTRAS
	protected AbstractHashtableOpenAddress<HashtableElement> tableTamPar;
	protected AbstractHashtableOpenAddress<HashtableElement> tableTamImpar;
	protected AbstractHashtableOpenAddress<HashtableElement> tableUnitaria;
	protected AbstractHashtableOpenAddress<HashtableElement> tableZero;

	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
		// o tamanho real utilizado vai ser PROPOSED_SIZE
		table1.insert(new HashtableElement(10)); // coloca no slot indexado com
													// 0
		table1.insert(new HashtableElement(15)); // coloca no slot indexado com
													// 5
		table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
		table1.insert(new HashtableElement(12)); // coloca no slot indexado com
													// 8, teve 2 colisoes
		table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
		table1.insert(new HashtableElement(8)); // coloca no slot indexado com
												// 6, teve 1 colisao

		table2 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
		
		// EXTRAS
		tableTamPar = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(6, HashFunctionClosedAddressMethod.DIVISION);
		tableTamImpar = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(5, HashFunctionClosedAddressMethod.DIVISION);
		int number = 1;
		while (number < 5) {
			tableTamPar.insert(new HashtableElement(number));
			tableTamImpar.insert(new HashtableElement(number));
			number++;
		}
		tableUnitaria = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(1, HashFunctionClosedAddressMethod.DIVISION);
		tableZero = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(0, HashFunctionClosedAddressMethod.DIVISION);
	}

	@Test
	public void testInsert() {
		assertEquals(3, table1.getCOLLISIONS());
		table1.insert(new HashtableElement(11)); // nao tem colisao. coloca no
													// slot indexado com 1
		assertEquals(3, table1.getCOLLISIONS());
		assertEquals(1, table1.indexOf(new HashtableElement(11)));

		table1.insert(new HashtableElement(21)); // tem 1 colisao. coloca no
													// slot indexado com 9
		assertEquals(4, table1.getCOLLISIONS());
		assertEquals(9, table1.indexOf(new HashtableElement(21)));

	}

	@Test
	public void testRemove() {
		table1.remove(new HashtableElement(17)); // elemento inexistente
		assertEquals(6, table1.size());

		table1.remove(new HashtableElement(12)); // elemento existente
		assertEquals(5, table1.size());
		assertNull(table1.search(new HashtableElement(12)));
	}

	@Test
	public void testSearch() {
		assertEquals(new HashtableElement(4),
				table1.search(new HashtableElement(4))); // elemento que existe
		assertNull(table1.search(new HashtableElement(14))); // elemento que nao
																// existe

	}

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
		table1.remove(new HashtableElement(15)); // esvazia
		table1.remove(new HashtableElement(8));
		table1.remove(new HashtableElement(12));
		table1.remove(new HashtableElement(2));
		table1.remove(new HashtableElement(10));
		table1.remove(new HashtableElement(4));
		assertTrue(table1.isEmpty());

		assertTrue(table2.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
		table1.insert(new HashtableElement(1)); // enche a tabela
		table1.insert(new HashtableElement(23));
		table1.insert(new HashtableElement(37));
		table1.insert(new HashtableElement(49));
		assertTrue(table1.isFull());

		assertFalse(table2.isFull());
	}

	@Test
	public void testSize() {
		assertEquals(6, table1.size());
		table1.insert(new HashtableElement(23));
		assertEquals(7, table1.size());
	}

	// TEXTES EXTRAS

	// Tabela Tamanho Par

	@Test
	public void testInsertRepetidoTamPar() {
		assertTrue(tableTamPar.size() == 4);
		// Não insere nenhum elemento pois já estão na tabela
		tableTamPar.insert(new HashtableElement(1));
		tableTamPar.insert(new HashtableElement(2));
		tableTamPar.insert(new HashtableElement(3));
		tableTamPar.insert(new HashtableElement(4));
		assertTrue(tableTamPar.size() == 4);
	}

	@Test(expected = HashtableOverflowException.class)
	public void testInsertOverflowTamPar() {
		tableTamPar.insert(new HashtableElement(0));
		tableTamPar.insert(new HashtableElement(5));
		tableTamPar.insert(new HashtableElement(6));
	}

	// Tabela Tamanho Impar

	@Test
	public void testInsertRepetidoTamImpar() {
		assertTrue(tableTamImpar.size() == 4);
		// Não insere nenhum elemento pois já estão na tabela
		tableTamImpar.insert(new HashtableElement(1));
		tableTamImpar.insert(new HashtableElement(2));
		tableTamImpar.insert(new HashtableElement(3));
		tableTamImpar.insert(new HashtableElement(4));
		assertTrue(tableTamImpar.size() == 4);
	}

	@Test(expected = HashtableOverflowException.class)
	public void testInsertOverflowTamImpar() {
		tableTamImpar.insert(new HashtableElement(0));
		tableTamImpar.insert(new HashtableElement(5));
	}

	// Tabela tamanho 1

	@Test
	public void testInsertUnitaria() {
		assertTrue(tableUnitaria.size() == 0);
		assertTrue(tableUnitaria.isEmpty());
		assertFalse(tableUnitaria.isFull());

		tableUnitaria.insert(new HashtableElement(1));

		assertTrue(tableUnitaria.size() == 1);
		assertFalse(tableUnitaria.isEmpty());
		assertTrue(tableUnitaria.isFull());
	}

	@Test(expected = HashtableOverflowException.class)
	public void testInsertRepetidoUnitaria() {
		// Lança exceção pois checa primeiro se a tabela está cheia antes de adicionar
		tableUnitaria.insert(new HashtableElement(1));
		tableUnitaria.insert(new HashtableElement(1));
	}

	// Tabela tamanho 0

	@Test(expected = HashtableOverflowException.class)
	public void testInsertTableZero() {
		// Lança exceção pois a tabela não tem espaços para guardar elementos
		assertTrue(tableZero.size() == 0);
		assertTrue(tableZero.isEmpty());
		assertTrue(tableZero.isFull());
		tableZero.insert(new HashtableElement(1));
	}

	// Testes passando elemento nulo como parâmeto

	@Test
	public void testInsereNulo() {
		assertTrue(tableTamPar.size() == 4);
		// Não insere elementos nulos
		tableTamPar.insert(null);
		assertTrue(tableTamPar.size() == 4);
	}

	@Test
	public void testRemoveNulo() {
		assertTrue(tableTamPar.size() == 4);
		// Não remove elementos nulos
		tableTamPar.remove(null);
		assertTrue(tableTamPar.size() == 4);
	}

	@Test
	public void testSearchNulo() {
		assertTrue(tableTamPar.search(null) == null);
	}

	@Test
	public void testIndexOfNulo() {
		assertTrue(tableTamPar.indexOf(null) == -1);
	}

}
