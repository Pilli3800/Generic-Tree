package node;

public class NodeAVL<T> {

	private T data;
	private NodeAVL<T> left;
	private NodeAVL<T> right;
	private int height;

	public NodeAVL(T data) {
		super();
		this.data = data;
		this.height = 0;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight() {
		int leftHeight, rightHeight;
		if (left == null) {
			leftHeight = 0;
		} else {
			leftHeight = left.getHeight();
		}
		if (right == null) {
			rightHeight = 0;
		} else {
			rightHeight = right.getHeight();
		}
		height = leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
	}

	public int getBalanceFactor() {
		int l, r;
		if (left == null) {
			l = 0;
		} else {
			l = left.getHeight();
		}
		if (right == null) {
			r = 0;
		} else {
			r = right.getHeight();
		}
		return l - r;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setLeft(NodeAVL<T> newLeft) {
		this.left = newLeft;
	}

	public void setRight(NodeAVL<T> newRight) {
		this.right = newRight;
	}

	public T getData() {
		return data;
	}

	public NodeAVL<T> getLeft() {
		return left;
	}

	public NodeAVL<T> getRight() {
		return right;
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

}
