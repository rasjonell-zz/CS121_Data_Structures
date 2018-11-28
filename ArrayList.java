
public class ArrayList<E> implements List<E> {
	public static final int CAPACITY = 16;
	private E[] data;
	private int size = 0;
	
	public ArrayList() { this(CAPACITY); }
	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public int size() { return data.length; }
	public boolean isEmpty() { return size == 0; }

	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}

	public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
		checkIndex(i, size);
		if (size == data.length) throw new IllegalStateException("Array is full");

		for (int k = size - 1; k >= i - 1; k--) {
			data[k + 1] = data[k];
		}
		
		data[i] = e;
		size++;
	}

	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];

		for (int k = i; k < size - 1; k++) {
			data[k] = data[k + 1];
		}

		size--;
		return temp;
	}

	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n) throw new IndexOutOfBoundsException("Illegal index " + i);
	}
}
