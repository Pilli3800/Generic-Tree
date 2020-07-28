package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import exception.ExceptionIsEmpty;
import exception.ItemDuplicated;
import exception.ItemNotFound;
import list.ListLinked;
import node.Node;

public class BinaryTree<T extends Comparable<T>> {
	private Node<T> root;

	public BinaryTree() {
		this.root = null;
	}

	public Node<T> getRoot() {
		return this.root;
	}

//	public ListLinked<T> ProductoPorTerminar(T producto) throws ExceptionIsEmpty {
//		if (this.isEmpty()) {
//			throw new ExceptionIsEmpty("Binary Search Tree Empty.");
//		} else {
//			ListLinked<T> productos = new ListLinked<T>();
//			return ProductoPorTerminarHelper(this.root, producto, productos);
//		}
//
//	}
//
//	private ListLinked<T> ProductoPorTerminarHelper(Node<T> node, T producto, ListLinked<T> resultLinkedList) {
//		int comparador = node.getData().compareTo(producto);
//		if (comparador < 0) {
//			resultLinkedList.insertFirst(node.getData());
//		}
//		if (node.getLeft() != null) {
//			this.ProductoPorTerminarHelper(node.getLeft(), producto, resultLinkedList);
//		}
//		if (node.getRight() != null) {
//			this.ProductoPorTerminarHelper(node.getRight(), producto, resultLinkedList);
//		}
//		return resultLinkedList;
//
//	}

	public String descendientes(T ele) throws ItemNotFound {
		if (search(ele) != null) {
			descendientesHelper(root, ele);
		}
		throw new ItemNotFound();
	}

	private void descendientesHelper(Node<T> node, T ele) {
		if (node.getData().compareTo(ele) == 0) {
			posfix(node); // Se puede cambiar segun como se quiera mostrar.
		}
		if (node.getData().compareTo(ele) < 0) {
			descendientesHelper(node.getRight(), ele);
		}
		if (node.getData().compareTo(ele) > 0) {
			descendientesHelper(node.getLeft(), ele);
		}
	}

	public ArrayList<T> longestTrajectory(Node<T> root) {
		if (root == null)
			return null;
		ArrayList<T> path = new ArrayList<T>();
		path.add(root.getData());
		ArrayList<T> check = longestTrajectoryHelper(longestTrajectory(root.getLeft()),
				longestTrajectory(root.getRight()));
		if (check != null)
			path.addAll(check);
		return path;
	}

	public ArrayList<T> longestTrajectoryHelper(ArrayList<T> path1, ArrayList<T> path2) {
		if (path1 == null && path2 == null) {
			return null;
		}
		if (path1 == null) {
			return path2;
		}
		if (path2 == null) {
			return path1;
		}
		if (path1.size() > path2.size()) {
			return path1;
		} else {
			return path2;
		}
	}

	public ArrayList<T> trajectoryTo(T data) throws ItemNotFound {
		if (search(data) != null) {
			ArrayList<T> path = new ArrayList<T>();
			trajectoryToHelper(this.root, data, path);
			Collections.reverse(path);
			return path;
		}
		throw new ItemNotFound();
	}

	private boolean trajectoryToHelper(Node<T> node, T data, ArrayList<T> path) {
		if (node == null)
			return false;
		if (node.getData().compareTo(data) == 0) {
			path.add(node.getData());
			return true;
		}
		boolean left_check = trajectoryToHelper(node.getLeft(), data, path);
		boolean right_check = trajectoryToHelper(node.getRight(), data, path);
		if (left_check == true || right_check == true) {
			path.add(node.getData());
			return true;
		}
		return false;

	}

	public void insert(T data) throws ItemDuplicated {
		if (this.isEmpty())
			this.root = new Node<T>(data);
		else {
			Node<T> parent = null;
			Node<T> aux = this.root;
			while (aux != null && !aux.getData().equals(data)) {
				parent = aux;
				if (aux.getData().compareTo(data) > 0)
					aux = aux.getLeft();
				else
					aux = aux.getRight();
			}
			if (aux != null) {
				throw new ItemDuplicated("Data " + data + " is duplicated");
			}
			Node<T> toInsert = new Node<T>(data);
			if (parent.getData().compareTo(data) > 0)
				parent.setLeft(toInsert);
			else
				parent.setRight(toInsert);
		}
	}

	public void insertRecursive(T x) throws ItemDuplicated {
		root = insert(x, root);
	}

	private Node<T> insert(T x, Node<T> current) throws ItemDuplicated {
		Node<T> aux = current;
		if (current == null) {
			aux = new Node<T>(x);
		} else {
			// buscamos el lugar para inserción
			int comparate = current.getData().compareTo(x);
			if (comparate == 0)
				throw new ItemDuplicated("The data " + x + " is duplicated.");
			if (comparate < 0)
				aux.setRight(insert(x, current.getRight()));
			else
				aux.setLeft(insert(x, current.getLeft()));
		}

		return aux;
	}
//		}

//	public void insertRecursive(T value) throws ItemDuplicated {
//		root = insert(root, value);
//	}
//
//	private Node<T> insert(Node<T> current, T data) throws ItemDuplicated {
//		if (current == null) {
//			return new Node<T>(data);
//		}
//		if (data.compareTo(current.getData()) < 0) {
//			current.setLeft(insert(current.getLeft(), data));
//
//		} else if (data.compareTo(current.getData()) > 0) {
//			current.setRight(insert(current.getRight(), data));
//
//		} else if (data.compareTo(current.getData()) == 0) {
//			throw new ItemDuplicated("Data Duplicated");
//		}
//		return current;
//	}

	public int heightNode(Node<T> node, T data) throws ExceptionIsEmpty {
		return heightNode(node, data, 0);
	}

	private int heightNode(Node<T> node, T data, int level) throws ExceptionIsEmpty {
		if (this.isEmpty()) {
			throw new ExceptionIsEmpty("Empty");
		}
		if (node == null) {
			return 0;
		}
		if (node.getData().compareTo(data) == 0) {
			return level;
		}
		int height = heightNode(node.getLeft(), data, level + 1);
		if (height != 0) {
			return height;
		}
		height = heightNode(node.getRight(), data, level + 1);
		return height;
	}

	public boolean isEmpty() {
		return this.root == null;
	}

	private void prefix(Node<T> node) {
		if (node != null) {
			System.out.print(node + ", ");
			prefix(node.getLeft());
			prefix(node.getRight());
		}
	}

	private void infix(Node<T> node) {
		if (node != null) {
			infix(node.getLeft());
			System.out.print(node + ", ");
			infix(node.getRight());
		}
	}

	private void posfix(Node<T> node) {
		if (node != null) {
			posfix(node.getRight());
			posfix(node.getLeft());
			System.out.print(node + ", ");
		}
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

	public void printTree() {
		this.root.printTree();
	}

	public void preOrder() {
		Stack<Node<T>> nodes = new Stack<Node<T>>();
		nodes.push(this.root);
		while (!nodes.isEmpty()) {
			Node<T> current = nodes.pop();
			System.out.print(current.getData() + ", ");
			if (current.getRight() != null) {
				nodes.push(current.getRight());
			}
			if (current.getLeft() != null) {
				nodes.push(current.getLeft());
			}
		}
	}

	public int countLeaf(Node<T> node) {
		if (node == null)
			return 0;
		if (node.getLeft() == null && node.getRight() == null)
			return 1;
		else
			return countLeaf(node.getLeft()) + countLeaf(node.getRight());
	}

	public int countNonleaf(Node<T> root) {
		if (root == null || (root.getLeft() == null && root.getRight() == null))
			return 0;
		return 1 + countNonleaf(root.getLeft()) + countNonleaf(root.getRight());
	}

	public int countNodes(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
	}

	public T search(T data) throws ItemNotFound {
		Node<T> aux = search(data, root);
		if (aux == null)
			throw new ItemNotFound("The data " + data + " is not found.");
		return aux.getData();
	}

	protected Node<T> search(T data, Node<T> node) {
		if (node == null)
			return null;
		else {
			int aux = node.getData().compareTo(data);
			if (aux < 0)
				return search(data, node.getRight());
			else if (aux > 0)
				return search(data, node.getLeft());
			else
				return node;
		}
	}

	public int height(Node<T> root) {
		if (root == null)
			return -1;
		else
			return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
	}

	public int areaTree() throws ExceptionIsEmpty {
		if (!isEmpty()) {
			int area = this.height(this.root) * this.countLeaf(this.root);
			return area;
		}
		throw new ExceptionIsEmpty("Empty");
	}

	public void remove(T x) throws ItemNotFound {
		this.root = remove(x, this.root);
	}

	protected Node<T> remove(T x, Node<T> actual) throws ItemNotFound {
		Node<T> res = actual;
		if (actual == null)
			throw new ItemNotFound("The data " + x + " is not found.");
		int resC = actual.getData().compareTo(x);
		if (resC < 0)
			res.setRight(remove(x, actual.getRight()));
		else if (resC > 0)
			res.setLeft(remove(x, actual.getLeft()));
		else if (actual.getLeft() != null && actual.getRight() != null) {// dos hijos
			res.setData(minRecover(actual.getRight()));
			res.setRight(minRemove(actual.getRight()));
		} else {
			res = (actual.getLeft() != null) ? actual.getLeft() : actual.getRight();
		}
		return res;
	}

	public T minRemove() {
		T min = minRecover(this.root);
		this.root = minRemove(this.root);
		return min;
	}

//	public ListLinked<T> ciudadPais(int i) throws ExceptionIsEmpty, LevelIsNotValid {
//	if (this.isEmpty()) {
//		throw new ExceptionIsEmpty("Binary Search Tree Empty.");
//	} else {
//		if (this.height(root) < i) {
//			throw new LevelIsNotValid("Level is not valid.");
//		} else {
//			ListLinked<T> resultLinkedList = new ListLinked<T>();
//			return ciudadPaisHelper(this.root, i, resultLinkedList);
//		}
//	}
//
//}
//
//private ListLinked<T> ciudadPaisHelper(Node<T> node, int i, ListLinked<T> resultLinkedList) {
//	if (i == 0) {
//		resultLinkedList.insertFirst(node.getData());
//		return resultLinkedList;
//	}
//	if (height(node) <= i) {
//		resultLinkedList.insertFirst(node.getData());
//	}
//
//	if (node.getLeft() != null) {
//		ciudadPaisHelper(node.getLeft(), i - 1, resultLinkedList);
//
//	}
//	if (node.getRight() != null) {
//		ciudadPaisHelper(node.getRight(), i - 1, resultLinkedList);
//
//	}
//
//	resultLinkedList.insertFirst(node.getData());
//
//	return resultLinkedList;
//
//}

	protected Node<T> minRemove(Node<T> actual) {
		if (actual.getLeft() != null) {
			actual.setLeft(minRemove(actual.getLeft()));
		} else {
			actual = actual.getRight();
		}
		return actual;
	}

	private T minRecover(Node<T> node) {
		assert (node != null);
		Node<T> min = node.getLeft();
		if (min == null) {
			return node.getData();
		} else {
			return minRecover(min);
		}
	}

	public String toString() {
		this.prefix(root);
		return null;

	}

}
