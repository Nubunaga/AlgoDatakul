import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is the class that will be used to create the <em> Double Circle Linked List</em></>
 * that will be used for the FIFO ADT. It will utilize a <em> Node </em> nestle class
 * set to <code> private </code> to only be able to be used by the programmer in. This class will also
 * implement the iterator so that it can go through the list. The code should also be able to remove the
 * first of the queue and last.
 *
 * @author Netanel Avraham Eklind
 * @version 1 2019-09-04
 *
 * Question:
 * < a href : https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 *  * question 5</>.
 *  There will be a implementation of the same code used in <dir> C:\Users\tomas\Desktop\AlgoData\Code\src\
 *  FundamentalsLabb\stack.java</dir>. And the iterator there
 *
 */

public class FIFO<Item> implements Iterable<Item>{
    private int stackSize;
    private Node<Item> first;
    private Node<Item> last;

    /**
     * Constructor for the Node object in the linked list
     * */
    private class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
        private int index;
    }
    /**
     * Constructor for the circle double linked list stack.
     * */
    public FIFO(){
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
     * Allows the user to remove the kth element depending on input index.
     * @param i an integer primitive value to be looked for in the list.
     * @return Item a generalised object.
     * */
    public Item removeKthNode(int i){
        Item item = popKthNode(i);
        indexRemove(i);
        printQueue();
        return item;
    }
    /**
     * Goes through the index and adds a new value, this when a
     * new node is added.
     * */
    private void indexAdd(){
        Node temp = first;
        while(temp.next != last.next){
            temp.index++;
            temp = temp.next;
        }
        if(temp.next == last.next)temp.index++;
    }
    public int getIndex(){
        return first.index;
    }
    /**
     * Removes the index to a specific value that have been removed.
     * @param i is the recently removed index.
     * */
    private void indexRemove(int i){
        Node temp = first;
        while(temp.index >= i){
            temp.index--;
            temp = temp.next;
        }
        if(temp.next == last.next)temp.index--;

    }
    /**
     * Prints the linked list
     * */
    private void printQueue(){
        Node node = first;
        while(node.next != last.next){
            System.out.print(" [" +node.item+"]Index:"+node.index+",");
            node = node.next;
        }
        if(node.next == last.next){
            System.out.print(" [" +node.item+"]Index:"+node.index+"");
        }
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
     * @return true /false
     * */
    public boolean isEmptyLast(){
        return last == null;
    }

    /**
     * Sets the new head.
     * @param item is the generic value to be added to node.
     * */
    private void setHead(Item item){
        first = new Node<>();
        first.item = item;
        first.index = 1;
        last = first;
        first.next = last;
        last.prev = first;

    }
    /**
     * Removes the Kth node from the list
     * @param i is the index value to look for.
     * @return the generic value present in the node
     * */
    private Item popKthNode(int i){
        Node<Item> temp = first;
        Item item;
        while(temp.index != i){
            temp = temp.next;
        }
        item = temp.item;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        return item;
    }

    /**
     * Inserts a new node into the list.
     * @param item contains generic value to be added to a node.
     * */
    private void newNode(Item item){
        Node oldNode = last;
        last = new Node<>();
        last.item = item;
        last.index = 0;
        last.next = first;
        last.prev = oldNode;
        oldNode.next = last;
        indexAdd();
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
    /**
     * Start function that the virtual machine calls to start the program.
     * @param args is a string array containing input from the user.
     * */
    public static void main(String[] args){


        FIFO fifo = new FIFO();

        fifo.enterQueue(1);
        fifo.enterQueue(2);
        fifo.enterQueue(3);
        fifo.enterQueue(4);
        fifo.enterQueue(5);
        fifo.enterQueue(6);
        fifo.enterQueue(7);

        fifo.removeKthNode(5);

        TestofFIFOIndex fifOtest = new TestofFIFOIndex();
        TestofFIFOIndex.Index [] arr = new TestofFIFOIndex.Index[7];
        for(int i = 1; i < arr.length; i++){
            arr[i] = fifOtest.enterQueue(i);
        }
        fifOtest.removeKthNode(arr[5]);
    }
}
