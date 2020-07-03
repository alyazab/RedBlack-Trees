package RedBlackTrees;

public class RBNode<T extends Comparable<T>> {
	
	static final boolean RED   = true;
    static final boolean BLACK = false;
    
	private RBNode<T> parent, leftChild, rightChild;
	private T key;
	private boolean color, nil;
	
	public RBNode(boolean nil) {
		this.setNil(nil);
		if(nil)
			setColor(RBNode.BLACK);
		else
			setColor(RBNode.RED);
	}
	
	// Parent
	public RBNode<T> getParent() {
		return parent;
	}

	public void setParent(RBNode<T> parent) {
		this.parent = parent;
	}
	
	// Right Child
	public RBNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(RBNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	// Left Child
	public RBNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(RBNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	// Key 
	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	// Null Node
	public boolean isNil() {
		return nil;
	}

	public void setNil(boolean nil) {
		this.nil = nil;
	}

	//Node Color
	
	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}
}
