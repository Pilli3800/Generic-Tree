package binarytree;

import node.NodeAVL;

public class BinaryTreeAVL<T extends Comparable<T>> {

	private NodeAVL<T> root;
	private int size;

	public BinaryTreeAVL() {
		size = 0;
	}

	public void printTree() {
		root.printTree();
	}

	public void insert(T data) {
		try {
			root = insert(root, new NodeAVL<T>(data));
			size++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private NodeAVL<T> insert(NodeAVL<T> current, NodeAVL<T> newNode) throws Exception {
		if (current == null) {
			current = newNode;
			current.setHeight();
		} else if (current.getData().compareTo(newNode.getData()) == 0) {
			throw new Exception();
		} else if (current.getData().compareTo(newNode.getData()) > 0) {
			current.setLeft(insert(current.getLeft(), newNode));
		} else if (current.getData().compareTo(newNode.getData()) < 0) {
			current.setRight(insert(current.getRight(), newNode));
		}
		current.setHeight();
		current = balanceNode(current);
		return current;
	}

	public boolean isEmpty() {
		return this.root == null;
	}

	public void route(int type) {
		switch (type) {
		case 1:
			this.prefix(this.root);
			break;
		case 2:
			this.infix(this.root);
			break;
		case 3:
			this.posfix(this.root);
			break;
		default:
			break;
		}
		System.out.println("\n");
	}

	private void prefix(NodeAVL<T> node) {
		if (node != null) {
			System.out.print(node.getData().toString() + ", ");
			prefix(node.getLeft());
			prefix(node.getRight());
		}
	}

	private void infix(NodeAVL<T> node) {
		if (node != null) {
			infix(node.getLeft());
			System.out.print(node.getData().toString() + ", ");
			infix(node.getRight());
		}
	}

	private void posfix(NodeAVL<T> node) {
		if (node != null) {
			posfix(node.getRight());
			posfix(node.getLeft());
			System.out.print(node.getData().toString() + ", ");
		}
	}

	private NodeAVL<T> balanceNode(NodeAVL<T> current) {
		NodeAVL<T> replacingNode = current;
		if (current.getBalanceFactor() == 2) {
			if (current.getLeft().getBalanceFactor() == -1) {
				current.setLeft(rotateLeft(current.getLeft()));
			}
			replacingNode = rotateRight(current);
		} else if (current.getBalanceFactor() == -2) {
			if (current.getRight().getBalanceFactor() == 1) {
				current.setRight(rotateRight(current.getRight()));
			}
			replacingNode = rotateLeft(current);
		}

		return replacingNode;
	}

	private NodeAVL<T> rotateLeft(NodeAVL<T> current) {
		if (current == root) {
			root = root.getRight();
			current.setRight(root.getLeft());
			root.setLeft(current);
			current.setHeight();
			root.setHeight();
			return root;
		} else {
			NodeAVL<T> rightC = current.getRight();
			current.setRight(rightC.getLeft());
			rightC.setLeft(current);
			current.setHeight();
			rightC.setHeight();
			return rightC;
		}
	}

	private NodeAVL<T> rotateRight(NodeAVL<T> current) {
		if (current == root) {
			root = root.getLeft();
			current.setLeft(root.getRight());
			root.setRight(current);
			current.setHeight();
			root.setHeight();
			return root;
		} else {
			NodeAVL<T> leftC = current.getLeft();
			current.setLeft(leftC.getRight());
			leftC.setRight(current);
			current.setHeight();
			leftC.setHeight();
			return leftC;
		}

	}

	public void remove(T data) {
		NodeAVL<T> temp = remove(root, data);
		if (temp != null) {
			root = temp;
			size--;
		}
	}

	private NodeAVL<T> remove(NodeAVL<T> current, T dataToRemove) {
		if (current == null) {
		} else if (current.getData().compareTo(dataToRemove) > 0) {
			current.setLeft(remove(current.getLeft(), dataToRemove));
		} else if (current.getData().compareTo(dataToRemove) < 0) {
			current.setRight(remove(current.getRight(), dataToRemove));
		} else if (current.getData() == dataToRemove) {
			NodeAVL<T> successor = findSuccessor(current);
			NodeAVL<T> successorParent = findParent(root, successor);
			if (successor == current) {
				return null;
			} else if (successorParent == current) {
				if (current.getData().compareTo(successor.getData()) < 0) {
					current.setRight(successor.getRight());
				} else {
					current.setRight(successor.getRight());
					current.setLeft(successor.getLeft());
				}
			} else if (successorParent.getLeft() != null) {
				successorParent.setLeft(null);
			}
			current.setData(successor.getData());
		}
		current.setHeight();
		current = balanceNode(current);
		return current;
	}

	private NodeAVL<T> findParent(NodeAVL<T> current, NodeAVL<T> child) {
		if (child == null) {
			return null;
		} else if (current.getData().compareTo(child.getData()) > 0) {
			if (current.getLeft() == child) {
				return current;
			} else {
				return findParent(current.getLeft(), child);
			}
		} else if (current.getData().compareTo(child.getData()) < 0) {
			if (current.getRight() == child) {
				return current;
			} else {
				return findParent(current.getRight(), child);
			}
		}
		return null;
	}

	private NodeAVL<T> findSuccessor(NodeAVL<T> current) {
		NodeAVL<T> successor;
		if (current == null) {
			successor = null;
		} else if (current.getRight() != null) {
			if (current.getLeft() == null) {
				successor = current.getRight();
			} else {
				successor = getMinimum(current.getRight());
			}
		} else if (current.getLeft() != null) {
			successor = current.getLeft();
		} else {
			successor = current;
		}
		return successor;
	}

	private NodeAVL<T> getMinimum(NodeAVL<T> current) {
		if (current.getLeft() != null) {
			return getMinimum(current.getLeft());
		} else {
			return current;
		}
	}

}
