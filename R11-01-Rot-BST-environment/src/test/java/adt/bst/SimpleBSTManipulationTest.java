package adt.bst;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SimpleBSTManipulationTest {
    
    BSTImpl<Integer> bst1 = new BSTImpl<Integer>();
	BSTImpl<Integer> bst2 = new BSTImpl<Integer>();
    BSTImpl<Integer> bstWithElements = new BSTImpl<Integer>();

    SimpleBSTManipulationImpl<Integer> sp = new SimpleBSTManipulationImpl<Integer>();

    @Before
	public void setUp() {
		fillTree(bstWithElements);
	}

    private void fillTree(BSTImpl<Integer> tree) {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

    private void fillTree2(BSTImpl<Integer> tree) {
		Integer[] array = { 5, 22, -35, 4, 8, 1, -1, 75, 11, 66, 231, -41 };
		for (int i : array) {
			tree.insert(i);
		}
	}

    private void fillTreeExtra(BSTImpl<Integer> tree) {
        tree.insert(5);
		tree.insert(3);
		tree.insert(-1);
		tree.insert(4);
		tree.insert(8);
		tree.insert(10);
		tree.insert(7);
    }

    // TEST EQUALS

    @Test
    public void testEqualsEmpty() {
        assertTrue(sp.equals(bst1, bst2));
    }

    @Test
    public void testEqualsOneElement() {
        bst1.insert(4);
        bst2.insert(4);
        assertTrue(sp.equals(bst1, bst2));
    }

    @Test
    public void testEqualsOnlyOneEmpty() {
        fillTree(bst1);
        assertFalse(sp.equals(bst1, bst2));
    }

    @Test
    public void testEqualsTree() {
        fillTree(bst1);
        fillTree(bst2);
        assertTrue(sp.equals(bst1, bst2));
        bst1.remove(bst1.getRoot().getData());
        bst2.remove(bst2.getRoot().getData());
        assertTrue(sp.equals(bst1, bst2));
    }

    @Test
    public void testNotEqualsTree() {
        fillTree(bst1);
        fillTreeExtra(bst2);
        assertFalse(sp.equals(bst1, bst2));
    }

    // TEST IS_SIMILAR

    @Test
    public void testIsSimilarEmpty() {
        assertTrue(sp.isSimilar(bst1, bst2));
    }

    @Test
    public void testIsSimilarOneSameElement() {
        bst1.insert(4);
        bst2.insert(4);
        assertTrue(sp.isSimilar(bst1, bst2));
    }

    @Test
    public void testIsSimilarOneDifferentElement() {
        bst1.insert(9);
        bst2.insert(4);
        assertTrue(sp.isSimilar(bst1, bst2));
    }

    @Test
    public void testIsSimilarTreeSameElements() {
        fillTree(bst1);
        fillTree(bst2);
        assertTrue(sp.isSimilar(bst1, bst2));
        bst1.remove(bst1.getRoot().getData());
        bst2.remove(bst2.getRoot().getData());
        assertTrue(sp.isSimilar(bst1, bst2));
    }

    @Test
    public void testIsSimilarTreeDifferentsElements() {
        fillTree(bst1);
        fillTree2(bst2);
        assertTrue(sp.isSimilar(bst1, bst2));
    }

    @Test
    public void testNotSimilarTree() {
        fillTree(bst1);
        fillTreeExtra(bst2);
        assertFalse(sp.isSimilar(bst1, bst2));
    }

    @Test
    public void testExtraNotSimilarTree() {
        fillTree(bst1);
        fillTree2(bst2);
        assertTrue(sp.isSimilar(bst1, bst2));
        bst2.insert(100);
        assertFalse(sp.isSimilar(bst1, bst2));
    }

    // TEST ORDER_STATISTIC

    @Test
    public void testOrderStatisticTreeEmpty() {
        assertTrue(sp.orderStatistic(bst1, 1) == null);
    }

    @Test
    public void testOrderStatisticInvalidK() {
        assertTrue(sp.orderStatistic(bstWithElements, 0) == null);
        assertTrue(sp.orderStatistic(bstWithElements, 13) == null);
    }

    @Test
    public void testOrderStatisticValid() {
        // Order: -40 -34 0 2 5 6 9 12 23 67 76 232
        assertTrue(sp.orderStatistic(bstWithElements, 1) == -40);
        assertTrue(sp.orderStatistic(bstWithElements, 2) == -34);
        assertTrue(sp.orderStatistic(bstWithElements, 12) == 232);
        assertTrue(sp.orderStatistic(bstWithElements, 11) == 76);
    }

    @Test
    public void testOrderStatisticOneElement() {
        bst1.insert(6);
        assertTrue(sp.orderStatistic(bst1, 0) == null);
        assertTrue(sp.orderStatistic(bst1, 2) == null);
        assertTrue(sp.orderStatistic(bst1, 1) == 6);
    }

}