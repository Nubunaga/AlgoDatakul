import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class will create an general ordered queue based on the ADT <em> QUEUE </em>
 * and will be implemented in a <em> FIFO</em> mather meaning the first that goes in
 * is the last to go out, however. Since the task is to make the queue be a priority
 * queue, we have to rethink this approach.
 * This means that instead of having the traditional queue, we have to rearrange them
 * so the queue goes from highest to lowest, meaning the lowest number will be the last
 * nr.
 * An heavy usage of abstraction to hide the algorithm from the public interface has been
 * made, thus protecting it from any changes from the public interface.
 *
 * @author Netanel Avraham Eklind
 * @version 1 2019-04/09: Implementing code and importing old queue system.
 * @version 2 2019-06/9 : Bug fixes of the sorting algorithm.
 * <ref> C:\Users\tomas\Desktop\AlgoData\Fråga 5\src.FIFO.java</ref>
 * Also the Same FIFO concept from last question i.e 5.
 * */

public class OrderdQueue<Item> implements Iterable<Item>{
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
    public OrderdQueue(){
        first = null;
        last = null;
        stackSize = 0;
    }

    /**
     * Enter the queue in a FIFO mather
     * @param item generic input value.
     * */
    public void enterQueue(Item item){
        Node<Item> node = new Node<>();
        node.item = item;
        if(isEmpty()){
            setLast(node);
        }
        else{
            newNode(node);
        }
        stackSize++;
        printQueue();
    }
    /**
     * Returns the state of the first (last) node in the system.
     * @return true if it is null, false if has a node in it.
     */
    public boolean isEmpty(){
        return last == null;
    }

    private void newNode(Node<Item> node){
        Node<Item> temp = last;
        if((int) node.item <= (int) temp.item ){
           temp.next = node;
           node.prev = temp;
           node.next = first;
           last = node;
        }
        else{
            orderInsert(node,temp);
        }

    }

    private void orderInsert(Node<Item> node, Node<Item> temp){
        boolean flag = false;
        while (temp != first){
            if((int) node.item <= (int) temp.item){
                node.next = temp.next;
                temp.next = node;
                node.prev = temp;
                flag = true;
                break;
            }
            else{
                temp = temp.prev;
            }
        }
     if(!flag)orderHeadofQueue(node,temp);
    }

    private void orderHeadofQueue(Node<Item> node, Node<Item> temp){
        if(((int)temp.item <= (int)node.item)){
            node.next = temp;
            temp.prev = node;
            first = node;
            node.prev = last;
        }
        else {
            node.next = temp.next;
            temp.next = node;
            node.prev = temp;
        }
    }

    private void setLast(Node node){
        last = node;
        first = node;
        last.next = first;
        last.prev = first;
    }
    private void printQueue(){
        int count = 0;
        Node node = first;
        if(stackSize == 1){
            System.out.println("["+node.item+"] ");
        }
        else {
            while (count < stackSize) {
                System.out.print("[" + node.item + "] ");

                node = node.next;
                count++;
            }
            System.out.print("\n");
        }
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
