package varu4300;

public class Node<A>
{

	
    private A  element; //element of type Poly
    private Node<A>  next; //points to next element

    public Node() {
    }
    /**
     * Constructor initializes Node
     * 
     * @param x
     * @param node
     */
    public Node(A x, Node<A> node)
    {
        element = x;
        next = node;
    }
    /**
     * Set Element method, updates element of type Poly
     * 
     * @param el 
     */
	public void setElement(A el) {
		element = el;
	}
	/**
     * Set Node method, points at next node
     * 
     * @param n 
     */
	public void setNext(Node<A> n) {
		next = n;
	}
	/**
     * Get Element method, gets the current element of type Poly
     * 
     * 
     * @return element 
     */
    public A getElement()
    {
        return element;
    }
    /**
     * Get Next method, points to the next element
     * 
     * @return next element in node
     */
    public Node<A> getNext()
    {
        return next;
    }
    /**
     * To String method 
     * 
     * @return string element in node
     */
    public String toString() {
    	return "Node contains "+element;
    }

}
