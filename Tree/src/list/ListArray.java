package list;

import java.util.Arrays;

public class ListArray<T extends Comparable<T>> implements ListTDA<T> {

	private Object[] content;
	private int size;

	ListArray(int size) {
		content = new Object[size];
		this.size = size;
	}

	@Override
	public void initializeList() {
		for (int i = 0; i < size; i++) {
			this.content[i] = null;
		}
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int length() {
		return this.size;
	}

	@Override
	public boolean search(T data) {
		if (isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < length(); i++) {
				if (data.equals(content[i])) {
					return true;
				}
			}
		}
		return false;
	}

	public void insertFirst(T data) {
		Object[] aux = new Object[++size];
		for (int i = 0; i < aux.length; i++) {
			if (i == 0) {
				aux[i] = data;
			}
			if (i > 0) {
				aux[i] = this.content[i - 1];
			}
		}
		this.content = aux;
	}

	@Override
	public void insertLast(T data) {
		if (isEmpty()) {
			content[0] = data;
		} else {
			for (int i = 0; i < size; i++) {
				if (content[i] == null) {
					content[i] = data;
					break;
				}
			}
		}

	}

	@Override
	public void remove(T data) {

		if (data == null) {
			for (int i = 0; i < this.size;) {
				if (content[i] == null)
					remake(i);
			}
		} else {
			for (int i = 0; i < size - 1; i++) {
				if (data.equals(content[i])) {
					remake(i);
				}
			}
		}

	}

	private void remake(int index) {
		int newsize = size - index - 1;
		if (newsize > 0)
			System.arraycopy(content, index + 1, content, index, newsize);
		content[--size] = null;
	}

	public void destroy() {
		initializeList();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public T searchK(int position) {
		for (int i = 0; i == position; i++) {
			return (T) content[i];
		}
		return null;

	}

	public int getIndex(T data) {
		int iterator = 0;
		if (isEmpty()) {
			return iterator;
		} else {
			for (int i = 0; i < length(); i++) {
				if (data.equals(content[i])) {
					return iterator;
				}
				iterator++;
			}
		}

		return -1;
	}

	public Object get(int index) {
		return content[0];
	}

	@Override
	public String toString() {
		String array = "";
		array = Arrays.toString(content);
//		for (int i = 0; i < size; i++) {
//			if (content[i] != null) {
//				array = array + content[i].toString() + "\n";
//			}
//		}
		return array;
	}

}
