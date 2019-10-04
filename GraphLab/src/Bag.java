import java.util.Iterator;

/**
 * Compilation: javac Bag.java
 * Execution: java Bag.java
 * Dependencies: None
 * Input: Item, a generic value
 * ########################################################################
 * Output:
 * ------------------------------------------------------------------------
 * ------------------------------------------------------------------------
 * ########################################################################
 * This class implements a bag ADT that stores an element in the node.
 * This will create a bag containing nodes that only can add and not remove.
 *
 * @author Netanel Avraham Eklind
 * @version 1: 2019-10/01: Implement the bag code.
 * Inspiration from <a href: Algorithm, 4th>
 * chapter 1, section 4</a>
 */

public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    private class Node {
        Item item;
        Node next;
    }

    public Bag (){
        first = null;
        size = 0;
    }

    /**
     * Adds the item to the linked list bag
     * @param item contains the new item to be added.
     */
    public void add(Item item) {
        if(isEmpty()){
            first = new Node();
            first.item = item;
            assert check();
        }
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            assert check();
        }
    }
    /**
     * checks if first node is null, empty
     * @return true if empty.
     * */
    private boolean isEmpty() {
        return first == null;
    }

    public String toString(){
        return "["+first.item+"] ";
    }

    /**
     * Returns a new iterator for the class
     *
     * @return iterator that goes through the list.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements  Iterator<Item>{

        private Node current = first;

        /**§
         * Checks if the current node has a value or not.
         * @return true or false
         * */
        public boolean hasNext(){
            return current != null;
        }

        /**
         * Updates to a new node in the list
         * @return item containing the node value.
         * */
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    /************************************************************************************
     * Checks if the code works correctly
     *************************************************************************************/
    private boolean check(){
    if (!sizeIncrease()) System.out.println("The size has not been increased");
    if (!nodesContainsItem()) System.out.println("The node does not contain a new item");
    return sizeIncrease() && nodesContainsItem();
    }

    // size have increased.
    private boolean sizeIncrease(){
        return size != 0;
    }

    // all new node contains items.
    private boolean nodesContainsItem(){
        Node temp = first;
        while(temp.next != null){
            if(temp.item == null){
                return false;
            }
        }
        return true;
    }
}

