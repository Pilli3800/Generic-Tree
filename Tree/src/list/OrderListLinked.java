package list;

public class OrderListLinked<T extends Comparable<T>> extends ListLinked<T> {

	public OrderListLinked() {
		super();
	}


	public void insert(T data) {
		if ((isEmpty()) || (data.compareTo(this.head.getData()) < 0)) {
			this.head = new Node<T>(data, this.head);
		} else { 
			append(this.head, data);
		}

		this.size++;
	}

	private void append(Node<T> node, T data) {
		assert (node != null);
		assert (!(data.compareTo(node.getData()) < 0));
		if ((node.getNext() == null) || 
				(data.compareTo(node.getNext().getData()) < 0)) { 
			node.setNext(new Node<T>(data, node.getNext()));
		} else { 
			append(node.getNext(), data);
		}
	}

	public boolean search(T data) {
		Node<T> aux = this.head;

		while (aux != null && (aux.getData()).compareTo(data) < 0)
			aux = aux.getNext();

		if (aux != null)
			return aux.getData().equals(data);

		return false;

	}

	public void remove(T data) {
		Node<T> aux = this.head;

		if (isEmpty())
			return;

		if (this.head.getData().compareTo(data) == 0) {
			this.head = this.head.getNext();
			this.size--;

		} else {
			while (aux.getNext() != null && aux.getData().compareTo(data) < 0)
				aux = aux.getNext();
			if (aux.getNext() != null) {
				aux.setNext(aux.getNext().getNext());
				this.size--;
			}
		}

	}

}
