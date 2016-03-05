package varu4300;

public class Stack<A>
{

    private Node<A> top = null; //top of stack
   /**
    *Constructor  Initializes the stack 
    */

    public void push(A x)
    {
        Node<A> node =  new Node<A>(x, this.top);
        this.top = node;
    }
    /**
     * Pop Method, pops the value at the top of the stack and 
     * removes value from stack
     * 
     * @return node.getElement()
     */

    public A pop()
    {
        Node<A> node = this.top;
        this.top = this.top.getNext();
        return node.getElement();
    }
    /**
     * Empty Method, checks if the stack is empty or not
	 *
     * @return this.top
     */
    public boolean isEmpty()
    {
        return this.top == null;
    }
    /**
     * Peek Method, gets the value at the top of the stack and doesn't remove it
     * 
     * @return str
     */
    public A peek()
    {
        return this.top.getElement();
    }

   public String toString() {
    	String str;
    	if (this.top==null)
    	   str = "Stack is empty";
    	else
    	   str = "Stack is not empty and top "+ this.top.toString();
    	return str;
    }
 
}