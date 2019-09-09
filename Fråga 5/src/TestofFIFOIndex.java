import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is the class that will be used to create the <em> Double Circle Linked List</em></>
 * that will be used for the FIFO ADT. It will utilize a <em> Node </em> nestle class
 * set to <code> private </code> to only be able to be used by the programmer in. This class will also
 * implement the iterator so that it can go through the list. The code should also be able to remove the
 * first of the queue and last. This will be done by having a nested class that gives a reference with the id
 * to a node. Meaning one can remove the node without looking for it, at the cost of memory space.
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

public class TestofFIFOIndex<Item> implements Iterable<Item>{
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
        private Index index;
    }

    public class Index<Item>{
        private Node node = first;
        private int id;
    }
    /**
     * Constructor for the circle double linked list stack.
     * */
    public TestofFIFOIndex(){
        first = null;
        last = null;
        stackSize = 0;
    }

    /**
     * Push method for the circle list, making sure to push the Node correct.
     * */
    public Index enterQueue(Item i){
        if(isEmptyLast()){
           return setHead(i);
        }
        else{
           return newNode(i);
        }
    }
    /**
     * Allows the user to remove the kth element depending on input index.
     * @param id is the node ID to be removed.
     * @return Item a generalised object.
     * */
    public Item removeKthNode(Index<Item> id){
        id.node.prev.next = id.node.next;
        id.node.next.prev = id.node.prev;
        indexRemove(id.node.index.id);
        printQueue();
        return (Item) id.node.item;
    }
    /**
     * Checks if the last, last node, is filled.
     * */
    public boolean isEmptyLast(){
        return last == null;
    }

    public int getIndex(){
        return first.index.id;
    }
    /**
     * Checks if the first, first node, is filled.
     * */
    public boolean isEmptyFirst(){
        return first == null;
    }

    /**
     * Adds a new index value to the list.
     * */
    private void indexAdd(){
        Node temp = first;
        while(temp.next != last.next){
            temp.index.id++;
            temp = temp.next;
        }
        if(temp.next == last.next)temp.index.id++;
    }
    /**
     * Removes index values up to the last removed index.
     * */
    private void indexRemove(int i){
        Node temp = first;
        while(temp.index.id > i){
            temp.index.id--;
            temp = temp.next;
        }
        if(temp.next == last)temp.index.id--;

    }
    /**
     * Prints the linked list.
     * */
    private void printQueue(){
        Node node = first;
        while(node.next != last.next){
            System.out.print(" ["+node.item+"]Index:"+node.index.id+",");
            node = node.next;
        }
        if(node.next == last.next){
            System.out.print(" ["+node.item+"]Index:"+node.index.id+" ");
        }
        System.out.print("\n");
    }

    /**
     * Sets the head of the list with a new index
     * @param item is the generic value to add.
     * */
    private Index setHead(Item item){
        first = new Node<>();
        first.item = item;
        first.index = new Index();
        first.index.id = 1;
        first.index.node = first;
        last = first;
        first.next = last;
        last.prev = first;
        stackSize++;
        printQueue();
        return first.index;
    }
    /**
     * Insert a new node to the list
     * @param item is the generic value to add.
     * */
    private Index newNode(Item item){
        Node oldNode = last;
        last = new Node<>();
        last.item = item;
        last.index = new Index();
        last.index.id = 0;
        last.index.node = last;
        last.next = first;
        last.prev = oldNode;
        oldNode.next = last;
        indexAdd();
        stackSize++;
        printQueue();
        return last.index;
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
