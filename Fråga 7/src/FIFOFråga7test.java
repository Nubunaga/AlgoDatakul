import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is the class that will be used to create the <em> Double Circle Linked List</em></>
 * that will be used for the FIFOFråga7test ADT. It will utilize a <em> Node </em> nestle class
 * set to <code> private </code> to only be able to be used by the programmer in. This class will also
 * implement the iterator so that it can go through the list. The code should also be able to remove the
 * first of the queue and last.
 *
 * @author Netanel Avraham Eklind
 * @version 1 2019-09-04
 *
 * Question:
 * < a href : https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 *  * question 3 and 4</>.
 *  There will be a implementation of the same code used in <dir> C:\Users\tomas\Desktop\AlgoData\Code\src\
 *  FundamentalsLabb\stack.java</dir>. And the iterator there
 *
 */

public class FIFOFråga7test<Item> implements Iterable<Item>{
    private int stackSize;
    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }
    /**
     * Constructor for the circle double linked list stack.
     * */
    public FIFOFråga7test(){
        first = null;
        last = null;
        stackSize = 0;
    }

    /**
     * Push method for the circle list, making sure to push the Node correct.
     * */
    public void enterQueue(Item i){
        if(isEmptyLast()){
            setHead(i);
        }
        else{
            newNode(i);
        }
        stackSize++;
        printQueue();
    }
    /**
     * Takes the <code> Item </code> from the list and then removes the node with that item.
     * @return Item  generic Item with any value.
     * @throws NoSuchElementException if there is no element in queue.
     * */
    public Item deenterQueue(){
        if(isEmptyLast())throw new NoSuchElementException();
        return popedNode();
    }
    /**
     * Takes the end of the list and removes that node.
     * @throws
     * */
    public Item removeLast(){
        if(isEmptyLast());
       return deenterQueue();
    }

    public Item removeFront(){
        if(isEmptyFirst())throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        first.prev = last;
        printQueue();
        return item;
    }

    /**
     * Checks the ()first queue in the line.
     * @return the generic item.
     * @throws NoSuchElementException if there is no element in queue
     * */
    public Item peek(){
        if (isEmptyLast()) throw new  NoSuchElementException();
        return first.item;
    }

    private void printQueue(){
        Node node = first;
        if(stackSize < 1)System.out.print(node.item);
        while(node.next != last.next){
            System.out.print(node.item);
            node = node.next;
        }
        System.out.print(node.item);
        System.out.print("\n");
    }

    /**
     * Returns the size of the queue.
     * @return the size of the queue.
     * */
    public int queueSize(){
        return stackSize;
    }
    /**
     * Checks if the last, last node, is filled.
     * */
    public boolean isEmptyLast(){
        return last == null;
    }

    /**
     * Checks if the first, first node, is filled.
     * */
    public boolean isEmptyFirst(){
        return first == null;
    }

    private void setHead(Item item){
        first = new Node<>();
        first.item = item;
        last = first;
        first.next = last;
        last.prev = first;
    }

    private Item popedNode(){
        Item item = first.item;
        first = first.next;
        first.prev = last;
        printQueue();
        return item;
    }

    private void newNode(Item item){
        Node oldNode = last;
        last = new Node<>();
        last.item = item;
        last.next = first;
        last.prev = oldNode;
        oldNode.next = last;
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
