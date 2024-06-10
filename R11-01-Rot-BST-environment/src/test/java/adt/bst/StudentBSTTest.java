package adt.bst;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bt.BTNode;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

	// Extra:
	private BSTImpl<Integer> treeWithElements = new BSTImpl<Integer>();

	@Before
	public void setUp() {
		tree = new BSTImpl<>();

		// Extra:
		treeWithElements.insert(5);
		treeWithElements.insert(3);
		treeWithElements.insert(-1);
		treeWithElements.insert(4);
		treeWithElements.insert(8);
		treeWithElements.insert(10);
		treeWithElements.insert(7);
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = 12;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));

	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(new Integer(-40), tree.search(-40).getData());
		assertEquals(new Integer(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
	}








	// TESTES EXTRAS

	private BSTImpl<Integer> tree1 = new BSTImpl<Integer>();

	// Test Root

	@Test
	public void testGetRootTreeEmpty() {
		assertEquals(NIL, tree1.getRoot());
	}

	// Test insertion

	@Test
	public void testFirstInsertion() {
		tree1.insert(5);
		assertTrue(tree1.getRoot().getData() == 5);
	}

	// Test Search

	@Test
	public void testSearchTreeEmpty() {
		assertEquals(NIL, tree1.search(5));
	}

	@Test
	public void testSearchWithElements() {
		tree1.insert(5);
		assertEquals(new Integer(5), tree1.search(5).getData());
		tree1.insert(8);
		tree1.insert(3);
		assertEquals(new Integer(3), tree1.search(3).getData());
		assertEquals(new Integer(8), tree1.search(8).getData());
	}

	@Test
	public void testSearchWithElementsNotFound() {
		tree1.insert(5);
		tree1.insert(8);
		tree1.insert(3);
		assertEquals(NIL, tree1.search(2));
		assertEquals(NIL, tree1.search(9));
	}

	// Test height

	@Test
	public void testGetHeightTreeEmpty() {
		assertEquals(-1, tree1.height());
	}

	@Test
	public void testGetHeightOneElement() {
		tree1.insert(5);
		assertEquals(0, tree1.height());
	}

	@Test
	public void testGetHeightTreeWithElements() {
		tree1.insert(5);
		tree1.insert(8);
		assertEquals(1, tree1.height());
		tree1.insert(3); // Não muda a altura
		assertEquals(1, tree1.height());
		// Muda agora
		tree1.insert(2);
		assertEquals(2, tree1.height());
		tree1.insert(1);
		assertEquals(3, tree1.height());
	}

	
	// Test Maximum

	@Test
	public void testGetMaximumTreeEmpty() {
		assertEquals(null, tree1.maximum());
	}

	@Test
	public void testGetMaximumOneElement() {
		tree1.insert(5);
		assertEquals(new Integer(5), tree1.maximum().getData());
	}

	@Test
	public void testGetMaximumWithElements() {
		tree1.insert(5);
		tree1.insert(3);
		tree1.insert(8);
		assertEquals(new Integer(8), tree1.maximum().getData());
		tree1.insert(10);
		assertEquals(new Integer(10), tree1.maximum().getData());
	}

	// Test Minimum

	@Test
	public void testGetMinimumTreeEmpty() {
		assertEquals(null, tree1.minimum());
	}

	@Test
	public void testGetMinimumOneElement() {
		tree1.insert(5);
		assertEquals(new Integer(5), tree1.minimum().getData());
	}

	@Test
	public void testGetMinimumWithElements() {
		tree1.insert(5);
		tree1.insert(3);
		tree1.insert(8);
		assertEquals(new Integer(3), tree1.minimum().getData());
		tree1.insert(1);
		assertEquals(new Integer(1), tree1.minimum().getData());
	}

	// Test Sucessor

	@Test
	public void testGetSucessorTreeEmpty() {
		assertEquals(null, tree1.sucessor(2));
	}

	@Test
	public void testGetSucessorElementNotInTree() {
		assertEquals(null, tree1.sucessor(4));
	}

	@Test
	public void testGetSucessorOneElement() {
		tree1.insert(5);
		assertEquals(null, tree1.sucessor(5));
	}

	@Test
	public void testGetSucessorElementHasNotSucessor() {
		tree1.insert(5);
		tree1.insert(3);
		tree1.insert(8);
		assertEquals(null, tree1.sucessor(8));
	}

	@Test
	public void testGetSucessorElementWithSucessor() {
		tree1.insert(5);
		tree1.insert(3);
		tree1.insert(8);
		assertEquals(new Integer(8), tree1.sucessor(5).getData());
		tree1.insert(10);
		assertEquals(new Integer(10), tree1.sucessor(8).getData());
	}

	@Test
	public void testGetSucessorElementWithSucessorParent() {
		assertEquals(new Integer(4), treeWithElements.sucessor(3).getData());
		assertEquals(new Integer(3), treeWithElements.sucessor(-1).getData());
		assertEquals(new Integer(5), treeWithElements.sucessor(4).getData());
	}

	// Test Predecessor

	@Test
	public void testGetPredecessorTreeEmpty() {
		assertEquals(null, tree1.predecessor(2));
	}

	@Test
	public void testGetPredecessorElementNotInTree() {
		assertEquals(null, tree1.predecessor(4));
	}

	@Test
	public void testGetPredecessorOneElement() {
		tree1.insert(5);
		assertEquals(null, tree1.predecessor(5));
	}

	@Test
	public void testGetPredecessorElementHasNotPredecessor() {
		tree1.insert(5);
		tree1.insert(3);
		tree1.insert(8);
		assertEquals(null, tree1.predecessor(3));
	}

	@Test
	public void testGetPredecessorElementWithPredecessor() {
		tree1.insert(5);
		tree1.insert(3);
		tree1.insert(8);
		assertEquals(new Integer(3), tree1.predecessor(5).getData());
	}

	@Test
	public void testGetPredecessorElementWithPredecessorParent() {
		assertEquals(new Integer(8), treeWithElements.predecessor(10).getData());
		assertEquals(new Integer(7), treeWithElements.predecessor(8).getData());
		assertEquals(new Integer(5), treeWithElements.predecessor(7).getData());
	}

	// Test Pré Ordem

	@Test
	public void testGetPreOrderTreeEmpty() {
		Integer[] order = new Integer[0];
		assertArrayEquals(order, tree1.preOrder());
	}

	@Test
	public void testGetPreOrderTreeOneElement() {
		tree1.insert(7);
		Integer[] order = new Integer[]{ 7 };
		assertArrayEquals(order, tree1.preOrder());
	}

	@Test
	public void testGetPreOrderTreeWithElements() {
		Integer[] order = new Integer[]{ 5, 3, -1, 4, 8, 7, 10 };
		assertArrayEquals(order, treeWithElements.preOrder());
	}

	// Test Ordem

	@Test
	public void testGetOrderTreeEmpty() {
		Integer[] order = new Integer[0];
		assertArrayEquals(order, tree1.order());
	}

	@Test
	public void testGetOrderTreeOneElement() {
		tree1.insert(7);
		Integer[] order = new Integer[]{ 7 };
		assertArrayEquals(order, tree1.order());
	}

	@Test
	public void testGetOrderTreeWithElements() {
		Integer[] order = new Integer[]{ -1, 3, 4, 5, 7, 8, 10 };
		assertArrayEquals(order, treeWithElements.order());
	}

	// Test Pós Ordem

	@Test
	public void testGetPostOrderTreeEmpty() {
		Integer[] order = new Integer[0];
		assertArrayEquals(order, tree1.postOrder());
	}

	@Test
	public void testGetPostOrderTreeOneElement() {
		tree1.insert(7);
		Integer[] order = new Integer[]{ 7 };
		assertArrayEquals(order, tree1.postOrder());
	}

	@Test
	public void testGetPostOrderTreeWithElements() {
		Integer[] order = new Integer[]{ -1, 4, 3, 7, 10, 8, 5 };
		assertArrayEquals(order, treeWithElements.postOrder());
	}

}
