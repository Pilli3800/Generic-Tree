package dictionary;

class EntryDic<C extends Comparable<C>, V> implements Comparable<EntryDic<C, V>> {

	private C key;
	private V value;

	public C getKey() {
		return key;
	}

	public void setKey(C key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public EntryDic(C key, V value) {
		this.key = key;
		this.value = value;
	}

	public EntryDic(C key) {
		this(key, null);
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object dictionary) {
		return ((EntryDic<C, V>) dictionary).key.equals(this.key);
	}

	public int compareTo(EntryDic<C, V> dictionary) {
		return this.key.compareTo(dictionary.key);
	}

	public String toString() {
		return this.key + " => " + this.value;
	}
}
