package orderStatistic;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import problems.FloorBinarySearchImpl;

public class Tests {
 
    public static void main(String[] args) {
		QuickSelect<Integer> qs = new QuickSelect<Integer>();
		Integer[] a1 = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 };
		Integer[] a2 = new Integer[] { 6, 41, 32, 7, -26, 4, 37, 49, 11, 18, 36 };
		Integer[] a3 = new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 };
		Integer[] a4 = new Integer[] { 6, 6, 6, 6, 6, 6 };
		Integer[] a5 = new Integer[] { 6 };
		Integer[] a6 = new Integer[] {};

		qs.quickSelect(a3, 5);

		Integer[] biggestElements = new Integer[]{};
		biggestElements = a1;
		System.out.println(Arrays.toString(biggestElements));


		
		System.out.println("ao");

		assertTrue(qs.quickSelect(a2, 1) == -26);

		assertTrue(qs.quickSelect(a1, 0) == null);
		assertTrue(qs.quickSelect(a1, 11) == null);
		assertTrue(qs.quickSelect(null, 2) == null);
		assertTrue(qs.quickSelect(a1, 10) == 31);

		assertTrue(qs.quickSelect(a3, 1) == 0);
		assertTrue(qs.quickSelect(a3, 2) == 1);
		assertTrue(qs.quickSelect(a3, 3) == 3);
		assertTrue(qs.quickSelect(a3, 4) == 4);
		assertTrue(qs.quickSelect(a3, 5) == 4);
		assertTrue(qs.quickSelect(a3, 6) == 4);
		assertTrue(qs.quickSelect(a3, 7) == 5);

		assertTrue(qs.quickSelect(a4, 1) == 6);
		assertTrue(qs.quickSelect(a4, 3) == 6);
		assertTrue(qs.quickSelect(a4, 6) == 6);

		assertTrue(qs.quickSelect(a5, 1) == 6);
		assertTrue(qs.quickSelect(a6, 1) == null);
	}

    public static void main(String[] args) {
		KLargestOrderStatisticsImpl<Integer> kls = new KLargestOrderStatisticsImpl<Integer>();
		Integer[] a3 = new Integer[] { 4, 9, 3, 4, 0, 5, 5, 1, 4 };
		System.out.println(Arrays.toString(kls.getKLargest(a3, 0)));
	}

    public static void main(String[] args) {
		FloorBinarySearchImpl fb = new FloorBinarySearchImpl();
		Integer[] a1 = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 };
		Integer[] a2 = new Integer[] { 6, 41, 32, 7, -26, 4, 37, 49, 11, 18, 36 };
		Integer[] a3 = new Integer[] { 4, 9 };
		Integer[] a4 = new Integer[] { 6, 6, 6, 6, 6, 6 };
		Integer[] a5 = new Integer[] { 6 };
		Integer[] a6 = new Integer[] {};
		assertTrue(fb.floor(a1, 3) == null);
		assertTrue(fb.floor(a1, 4) == 4);
		assertTrue(fb.floor(a1, 5) == 4);
		assertTrue(fb.floor(a1, 6) == 4);
		assertTrue(fb.floor(a1, 7) == 7);
		assertTrue(fb.floor(a2, 0) == -26);

		assertTrue(fb.floor(a3, 3) == null);
		assertTrue(fb.floor(a3, 4) == 4);
		assertTrue(fb.floor(a3, 5) == 4);
		assertTrue(fb.floor(a3, 8) == 4);
		assertTrue(fb.floor(a3, 9) == 9);
		assertTrue(fb.floor(a3, 10) == 9);

		assertTrue(fb.floor(a4, 5) == null);
		assertTrue(fb.floor(a4, 6) == 6);
		assertTrue(fb.floor(a4, 7) == 6);

		assertTrue(fb.floor(a5, 5) == null);
		assertTrue(fb.floor(a5, 6) == 6);
		assertTrue(fb.floor(a5, 7) == 6);

		assertTrue(fb.floor(a6, 2) == null);
		assertTrue(fb.floor(a6, -2) == null);
		assertTrue(fb.floor(a6, null) == null);
	}

}