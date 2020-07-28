package dictionary;

import binarytree.BinaryTree;
import exception.ItemDuplicated;
import exception.ItemNotFound;

class BSTDictionary<C extends Comparable<C>, V> implements Dictionary<C, V> {

	private BinaryTree<EntryDic<C, V>> tree;

	public BSTDictionary() {
		tree = new BinaryTree<EntryDic<C, V>>();
	}

	public void insert(C key, V value) throws ItemDuplicated {
		tree.insert(new EntryDic<C, V>(key, value));
	}

	public void remove(C key) throws ItemNotFound {
		tree.remove(new EntryDic<C, V>(key));
	}

	public V search(C key) throws ItemNotFound {
		return tree.search(new EntryDic<C, V>(key)).getValue();
	}

	public boolean isEmpty() {
		return tree.isEmpty();
	}

	public String toString() {
		return tree.toString();
	}
}
