package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (super.isFull()) {
			throw new HashtableOverflowException();
		}
		if (element != null) {
			int probe = 0;
			boolean wasInserted = false;
			while (probe < super.capacity() && !wasInserted) {
				int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
				T elementTable = (T) super.table[hash];
				if (elementTable == null || elementTable.equals(super.deletedElement)) {
					super.table[hash] = element;
					super.elements++;
					wasInserted = true;
				} else {
					if (elementTable.equals(element)) {
						wasInserted = true;
					}
					super.COLLISIONS++;
					probe++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !super.isEmpty()) {
			int probe = 0;
			boolean wasRemoved = false;
			while (probe < super.capacity() && !wasRemoved) {
				int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
				if (super.table[hash] != null && super.table[hash].equals(element)) {
					super.table[hash] = deletedElement;
					super.elements--;
					wasRemoved = true;
				} else {
					probe++;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		T elementFound = null;
		if (element != null && !super.isEmpty()) {
			int probe = 0;
			boolean wasFound = false;
			while (probe < super.capacity() && !wasFound) {
				int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
				if (super.table[hash] != null && super.table[hash].equals(element)) {
					elementFound = (T) super.table[hash];
					wasFound = true;
				} else {
					probe++;
				}
			}
		}
		return elementFound;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		if (element != null && !super.isEmpty()) {
			int probe = 0;
			boolean wasFound = false;
			while (probe < super.capacity() && !wasFound) {
				int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
				if (super.table[hash] != null && super.table[hash].equals(element)) {
					index = hash;
					wasFound = true;
				} else {
					probe++;
				}
			}
		}
		return index;
	}

}
