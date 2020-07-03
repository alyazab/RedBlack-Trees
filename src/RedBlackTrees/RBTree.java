package RedBlackTrees;

import javax.management.RuntimeErrorException;

public class RBTree<T extends Comparable<T>> {

	private RBNode<T> RBRoot;

	public RBTree(T key){
		RBRoot = new RBNode<T>(false);
		
		this.RBRoot.setKey(key);
		RBRoot.setColor(false);
		
		RBNode<T> leftChild, rightChild;
		
		leftChild = new RBNode<T>(true);
		rightChild= new RBNode<T>(true);	
		
		leftChild.setParent(RBRoot);
		rightChild.setParent(RBRoot);
		
		RBRoot.setLeftChild(leftChild);
		RBRoot.setRightChild(rightChild);
		
	}
	
	// Getters and Setters for Root
	public RBNode<T> getRoot() {
		return RBRoot;
	}

	public void setRoot(RBNode<T> RBRoot) {
		this.RBRoot = RBRoot;
	}
	
	// Check if Tree is empty
	public boolean isEmpty() {
		if(RBRoot == null)
			return true;
		return false;
	}
	
	// Search in Tree
	public RBNode<T> searchRB(T Key) {
		
		// No defined Key
		if(Key == null)
			throw new RuntimeErrorException(null);
		
		// Empty Tree 
		if(isEmpty())
			return null;
		
		RBNode<T> searchedNode = searchNode(RBRoot, Key);
		if(searchedNode == null)
			return null;
		else
			return searchedNode;
	}
	
	// Recursive Search for Search function
	public RBNode<T> searchNode(RBNode<T> Root, T Key) {
		
		if(Root.isNil())
			return null;
		
		else if(Key.equals(Root.getKey()))
			return Root;
		
		else if (Key.compareTo(Root.getKey()) > 0)
			return searchNode(Root.getRightChild(), Key);
		
		else
			return searchNode(Root.getLeftChild(), Key);		
	}
	
	// ____________________________________________
	
	public void RotateRight(RBNode<T> node) {
		if(node.getParent()!=null) {
			if(node==node.getParent().getLeftChild()) {
				node.getParent().setLeftChild(node.getLeftChild());
			}
			else {
				node.getParent().setRightChild(node.getLeftChild());
			}
			node.getLeftChild().setParent(node.getParent());
			node.setParent(node.getLeftChild());
			if(node.getLeftChild().getRightChild()!=null) {
				node.getLeftChild().getRightChild().setParent(node);
			}
			node.setLeftChild(node.getLeftChild().getRightChild());
			node.getParent().setRightChild(node);
		}
		else {
			RBNode<T> LEFT = RBRoot.getLeftChild();
			RBRoot.setLeftChild(RBRoot.getLeftChild().getRightChild());
			if(RBRoot.getLeftChild()!=null) {
				LEFT.getRightChild().setParent(RBRoot);
				RBRoot.setParent(LEFT);
				LEFT.setRightChild(RBRoot);
				LEFT.setParent(null);
				RBRoot=LEFT;
			}
			else {
				RBRoot.setParent(LEFT);
				LEFT.setRightChild(RBRoot);
				LEFT.setParent(null);
				RBRoot=LEFT;
			}
		}
	}
	
	public void RotateLeft(RBNode<T> node) {
		if(node.getParent()!=null) {
			if(node==node.getParent().getLeftChild()) {
				node.getParent().setLeftChild(node.getRightChild());
			}
			else {
				node.getParent().setRightChild(node.getRightChild());
			}
			node.getRightChild().setParent(node.getParent());
			node.setParent(node.getRightChild());
			if(node.getRightChild().getLeftChild()!=null) {
				node.getRightChild().getLeftChild().setParent(node);
			}
			node.setRightChild(node.getRightChild().getLeftChild());
			node.getParent().setLeftChild(node);
		}
		else {
			RBNode<T> RIGHT = RBRoot.getRightChild();
			RBRoot.setRightChild(RIGHT.getLeftChild());
			if(RIGHT.getLeftChild()!=null) {
				RIGHT.getLeftChild().setParent(RBRoot);
				RBRoot.setParent(RIGHT);
				RIGHT.setLeftChild(RBRoot);
				RIGHT.setParent(null);
				RBRoot=RIGHT;
			}
			else {
				RBRoot.setParent(RIGHT);
				RIGHT.setLeftChild(RBRoot);
				RIGHT.setParent(null);
				RBRoot=RIGHT;
			}
		}
	}

	public void FixTree(RBNode<T> node) {		
		if(node.getParent() == null) {
			node = RBRoot;
			node.setColor(RBNode.BLACK); 
			return;
		}
		else if(node.getParent().isColor() == RBNode.BLACK)
			return;
		
		RBNode<T> Father = node.getParent();
		RBNode<T> GrandFather = node.getParent().getParent();
		RBNode<T> Uncle;

		if(Father == GrandFather.getLeftChild())
			Uncle = GrandFather.getRightChild();
		else
			Uncle = GrandFather.getLeftChild();
			
		if(Uncle.isColor() == RBNode.RED){
			Uncle.setColor(RBNode.BLACK);
			Father.setColor(RBNode.BLACK);
			GrandFather.setColor(RBNode.RED);
			FixTree(GrandFather);
			return;
		}
		
		else {
			
			if(Father == GrandFather.getLeftChild() && node == Father.getLeftChild()) {
				RotateRight(GrandFather);
				swapColor(GrandFather,Father);
			}
			
			else if(Father == GrandFather.getLeftChild() && node != Father.getLeftChild()) {
				RotateLeft(Father);
				node = Father;
				Father = node.getParent();
				GrandFather = Father.getParent();
				if(node.getParent() == GrandFather.getLeftChild())
					Uncle = GrandFather.getRightChild();
				else
					Uncle = GrandFather.getLeftChild();
				RotateRight(GrandFather);
				swapColor(GrandFather, Father);
			}
			
			else if(node == Father.getLeftChild()) {
				RotateRight(Father);
				node = Father;
				Father = node.getParent();
				GrandFather = Father.getParent();
				if(node.getParent() == GrandFather.getLeftChild())
					Uncle = GrandFather.getRightChild();
				else
					Uncle = GrandFather.getLeftChild();
				RotateLeft(GrandFather);
				swapColor(GrandFather, Father);
			}
			
			else {
				RotateLeft(GrandFather);
				swapColor(GrandFather, Father);
			}
		}
	}

	public void swapColor(RBNode<T> nodeOne, RBNode<T> nodeTwo) {
		boolean temp;
		temp = nodeOne.isColor();
		nodeOne.setColor(nodeTwo.isColor());
		nodeTwo.setColor(temp);
	}
	
	public boolean Insert(T key) {
		RBNode<T> temp = new RBNode<T>(true);
		temp =this.RBRoot ;
		
		while(temp.isNil() == false) {
			if(key.compareTo(temp.getKey()) == 0)
				return true;
			else if(key.compareTo(temp.getKey()) >= 0) {
				temp = temp.getRightChild();
			}
			
			else {
				temp = temp.getLeftChild();
			}
		}
		temp.setKey(key);
		temp.setColor(RBNode.RED);
		temp.setNil(false);
		
		temp.setRightChild(new RBNode<T>(true));
		temp.setLeftChild(new RBNode<T>(true));
		
		temp.getRightChild().setParent(temp);
		temp.getLeftChild().setParent(temp);

		FixTree(temp);
		return false;
	}
	
	// ____________________________________________
	
	public int printTreeHeight () {
		if(RBRoot.isNil())
			return 0;
		return height(RBRoot);
	}
	
	public int height(RBNode<T> Root) {
		if (Root.isNil()) return -1;
		return (1 + Math.max(height(Root.getRightChild()), height(Root.getLeftChild())));
	}
	
	public int printTreeSize () {
		if(RBRoot.isNil())
			return 0;
		return (recCountSize(RBRoot.getRightChild()) + recCountSize(RBRoot.getLeftChild()) + 1) + 1;
	}
	
	public int recCountSize (RBNode<T> Root) {
		if(Root.isNil())
			return 0;
		return (recCountSize(Root.getRightChild()) + recCountSize(Root.getLeftChild()) + 1);
	}
	
	private void printHelper(RBNode<T> RBRoot, String indent, boolean last) {
		// print the tree structure on the screen
	   	if (RBRoot != null) {
		   System.out.print(indent);
		   if (last) {
		      System.out.print("R----");
		      indent += "     ";
		   } else {
		      System.out.print("L----");
		      indent += "|    ";
		   }
	        
	       String sColor = RBRoot.isColor() == true?"RED":"BLACK";
		   System.out.println(RBRoot.getKey() + "(" + sColor + ")");
		   printHelper(RBRoot.getLeftChild(), indent, false);
		   printHelper(RBRoot.getRightChild(), indent, true);
		}
	}
	
	public void prettyPrint() {
	    printHelper(this.RBRoot, "", true);
	}
}