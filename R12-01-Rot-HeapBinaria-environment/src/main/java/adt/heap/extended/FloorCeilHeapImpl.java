package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer floor = null;
		if (array != null && array.length > 0) {
			for (Integer element : array) {
				super.insert(element);
			}
			boolean foundFloor = false;
			while (this.rootElement() != null && !foundFloor) {
				Integer root = this.extractRootElement();
				if (root == numero) {
					floor = root;
					foundFloor = true;
				} else if (root < numero) {
					if (floor == null || (floor != null && root > floor)) {
						floor = root;
					}
				} else if (floor != null) {
					foundFloor = true;
				}
			}
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer ceil = null;
		if (array != null && array.length > 0) {
			for (Integer element : array) {
				super.insert(element);
			}
			boolean foundCeil = false;
			while (this.rootElement() != null && !foundCeil) {
				Integer root = this.extractRootElement();
				if (root == numero) {
					ceil = root;
					foundCeil = true;
				} else if (root > numero) {
					if (ceil == null || (ceil != null && root < ceil)) {
						ceil = root;
					}
				} else if (ceil != null) {
					foundCeil = true;
				}
			}
		}
		return ceil;
	}

}
