import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is the class that will be used to create the <em> Double Circle Linked List</em></>
 * that will be used for the FIFO ADT. It will utilize a <em> Node </em> nestle class
 * set to <code> private </code> to only be able to be used by the programmer in. This class will also
 * implement the iterator so that it can go through the list. The code should also be able to remove the
 * first of the queue and last without damaging the rest of the program.
 *
 * @author Netanel Avraham Eklind
 * @version 1 2019-09-04
 *
 * Question:
 * < a href : https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 *  * question 3 </>.
 *  There will be a implementation of the same code used in <dir> C:\Users\tomas\Desktop\AlgoData\Code\src\
 *  FundamentalsLabb\stack.java</dir>. And the iterator there
 *  Runtime is: 0.03 seconds, at 100 items: 0.33 seconds.
 */

public class FIFO<Item> implements Iterable<Item>{
    private int stackSize;
    private Node<Item> first;
    private Node<Item> last;

    /**
     * private constructor for the nested node class.
     * */
    private class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
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
     * Takes the <code> Item </code> from the list and then removes the node with that item.
     * @return Item  generic Item with any value.
     * @throws NoSuchElementException if there is no element in queue.
     * */
    public Item deenterQueue(){
        if(isEmptyLast())throw new NoSuchElementException();
        return popedNode();
    }

    /**
     * Takes the last item in the node, witch being in the FIFO ADT the first item to remove.
     * @return the item in the first (last) node.
     * */
    public Item getLastItem(){
        return last.item;
    }
    /**
     * goes through the linked list and prints each node to the stdout and thus
     * goes to the screen.
     * */
    private void printQueue(){
        Node node = first;
        if(stackSize < 1)System.out.print("["+node.item+"]");
        while(node.next != last.next){
            System.out.print(" ["+node.item+"],");
            node = node.next;
        }
        System.out.print(" ["+ node.item+"]");
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

    /**
     * An algorithm that sets the head of the node.
     * @param item generic value item.
     * */
    private void setHead(Item item){
        first = new Node<>();
        first.item = item;
        last = first;
        first.next = last;
        last.prev = first;
    }
    /**
     * pops the node that has been recently added
     * @return the generic value of that node.
     * */
    private Item popedNode(){
        Item item = last.item;
        last = last.prev;
        last.next = first;
        printQueue();
        return item;
    }
    /**
     * Insert a new node into the queue.
     * @param item is the generic value to be placed in the node.
     * */
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

    /**
     * The function that takes the start argument and runs the program in the virtual machine.
     * @param args an array of string arguments that the user can input.
     * */
    public static void main(String[]args) {
        long startTime;
        long endTime;
        double runTime = 0;

        FIFO fifo = new FIFO();

        startTime = System.nanoTime();
        for(int i = 0; i <= 10;i++){
            fifo.enterQueue(i);
        }
        for(int i = fifo.queueSize(); i > 0; i-- ){
            fifo.deenterQueue();
        }
        for(int i = 1; i <= 9;i++){
            fifo.enterQueue(i);
        }
        System.out.print("\n");
        endTime = System.nanoTime();
        runTime = (endTime - startTime)/(1*Math.pow(10,9));
        System.out.print("Runtime: " + runTime);
    }
}
