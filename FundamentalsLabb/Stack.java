package FundamentalsLabb;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Timeline document(
 * @version 1 29/08 -2019
 * @version 2 30/08 - 2019
 * @version 3 31/8 - 2019
 * @version 4 1/9 - 2019
 * )
 *
 * The current program implement an Last In First Out (LIFO) type stack utilizing both
 * <code> pop </code> and <code> push </code> to receive the current first <code>
 * {@link Node} </code> item data in the stack.
 *
 * The code utilize the mention <em> push </em> and <em> pop </em> inorder to store new
 * value inside the generic <Code> Item </Code>. This makes the code be able to track
 * with the assistance of <code> {@link Stack} </code>, an object created that encapsulates
 * the nodes in an object, thus allowing for easy accessibility for the program.
 * The program uses an <code>{@link ListIterator} </code> in order to traverse through the line of linked list,
 * allowing for the program to access different parts of the chain, including
 * <code> Insert </code> elements.
 *
 * @author Netanel Avraham Eklind
 * with inspiration from
 * <a href = https://algs4.cs.princeton.edu/13stacks/> sektion 1.3 </a>
 * <i> Algorithms, 4th Edition </i>
 * and the code from FundamentalsLabb.Stack.java in that document linked here
 * <a href =https://algs4.cs.princeton.edu/13stacks/Stack.java.html>
 * </a>
 *
 * @param <Item> is a generic type of item in the stack.
 * */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int stackSize;

    private class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }
    /**
     * Constructor for the new stack object.
     * */
    public Stack(){
        first = null;
        stackSize = 0;
    }

    /**
     * Adds a new item to the stack, increasing the size and pushing the old further
     *      * back in the stack, if it is the first node that will just set a new one
     *      in the list.
     * @param item                  is the generic item that will be used.
     * */
    public void push(Item item){
        if(first == null){
            first = new Node<>();
            first.item = item;
        }
        else {
            newNode(item);
        }
            stackSize++;
    }

    /**
     * The method takes the first value in the stack and returns it to the user
     *
     * @return item value of the current node in the stack according to LIFO.
     * */
    public Item pop(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return popedNode();
    }
    /**
     * Takes the size of the current stack and returns it to the caller
     * @return stackSize, an integer data type with the current value.
     * */
    public int getSize() {
        return stackSize;
    }

    private boolean isEmpty(){
        return this.first == null;
    }
    /**
     * Inserts an element in a specific place according to input
     * @param item              new element to insert
     * @param stackElement      placement to insert element
     *                          into.
     * */
    public void insertElement(Item item, Item stackElement){
        Node temp = first;
        if(isEmpty())throw new NoSuchElementException();
        while(placement(temp,stackElement))temp = temp.next;
        insert(temp,item);
        stackSize++;
    }
    /**
     * Inserts an element in a specific place according to priority
     * of value.
     * @param item   contains the value of the new element.
     * */
    public void priorityInsert(Item item){
        Node temp = first;
        if(isEmpty())throw new NoSuchElementException();
        if((int) item > (int) (first.item))insert(temp,item);
        while(priority(temp,item))temp = temp.next;
        insert(temp,item);
        stackSize++;

    }

    /**
     * Overrides the current <code> toString </code> allowing for the stdout to show the
     * objects value instead of the reference to the memory address.
     * */
    public String toString() {
        StringBuilder sb = new StringBuilder();
    for(Item item : this){
        sb.append(item);
    }
        return sb.toString();
    }

    private void insert(Node temp,Item item){
       Node<Item> p = temp;
       Node<Item> n = new Node<>();
       n.item = item;
       n.next = p.next;
       p.next = n;
     }

    private boolean placement (Node temp ,Item stackElement){
        return temp.item != stackElement;
    }

    private void newNode(Item item){
        Node<Item> oldNode = first;
        first = new Node<>();
        first.item = item;
        first.next = oldNode;
    }

    private Item popedNode(){
        Item item = first.item;
        first = first.next;
        stackSize--;
        return item;
    }

    private boolean priority(Node temp ,Item item){
        return (int)item < (int) temp.next.item;
    }

    /**
     * Returns an interator to the current stack, thus having the
     * iterator, iterate through the items in the stack.
     *
     * @return ListIterator that is used to iterate through the stack
     * item.
     * */
    public Iterator<Item> iterator(){
        return  new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item>{
        private Node<Item> current;

        private ListIterator(Node<Item> first){
            this.current = first;
        }

        /**
         * Checks if the current node is linked to another node and returns
         * the <code> Item </code>.
         * @return Item an generic data value inside the node.
         * */
        public Item next(){
            if(!hasNext())throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        /**
         * Checks that the current node exist, meaning that
         * <code> next() </code> can take that item.
         * @return boolean true or false depending on the existence
         * of an node.
         * */
        public boolean hasNext(){
          return this.current != null;
        }

    }
}
