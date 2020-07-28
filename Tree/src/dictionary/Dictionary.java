package dictionary;

import exception.ItemDuplicated;
import exception.ItemNotFound;

public interface Dictionary<C, V> {

	void insert(C key, V value) throws ItemDuplicated;

	void remove(C key) throws ItemNotFound;

	V search(C key) throws ItemNotFound;

	boolean isEmpty();

}
