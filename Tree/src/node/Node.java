package node;

public class Node<T> {

	private T data;
	private Node<T> left;
	private Node<T> right;

	public Node(T data, Node<T> left, Node<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public Node(T data) {
		this(data, null, null);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public void printTree() {
		if (this.right != null) {
			right.printTree(true, "");
		}
		printNodeValue();
		if (this.left != null) {
			left.printTree(false, "");
		}
	}

	private void printTree(boolean isRight, String indent) {
		if (right != null) {
			right.printTree(true, indent + (isRight ? "      " : " |    "));
		}
		System.out.print(indent);
		if (isRight) {
			System.out.print(" /");
		} else {
			System.out.print(" \\");
		}
		System.out.print("-----");
		printNodeValue();
		if (left != null) {
			left.printTree(false, indent + (isRight ? " |    " : "      "));
		}
	}

	private void printNodeValue() {
		System.out.print(getData());
		System.out.print("\n");
	}

	@Override
	public String toString() {
		return this.data.toString();
	}

}
