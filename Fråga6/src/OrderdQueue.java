import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

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
 * runtime 0.0049 seconds.
 * */

public class OrderdQueue<Item> implements Iterable<Item>{
    private int stackSize;
    private Node<Item> first;
    private Node<Item> last;
    /**
     * Constructor for the node class.
     * */
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

    public Item deenterQueue(){
        Item item = last.item;
        last.prev.next = last.next;
        last = last.prev;
        stackSize--;
        printQueue();
        return item;
    }
    /**
     * Returns the state of the first (last) node in the system.
     * @return true if it is null, false if has a node in it.
     */
    public boolean isEmpty(){
        return last == null;
    }

    public Item getItem(){
        return first.item;
    }


    /**
     * Inserts a new method in the list.
     * @param node contains new value
     * */
    private void newNode(Node<Item> node){
        Node<Item> temp = last;
        if(nodeCompare(node,temp)){
           temp.next = node;
           node.prev = temp;
           node.next = first;
           last = node;
        }
        else{
            orderInsert(node,temp);
        }

    }
    /**
     * compares the item in the node
     * @param node contains the new value. in one case, the current.
     * @param temp is the temporary pointer to the current item.
     * */
    private boolean nodeCompare(Node<Item> node, Node <Item> temp){
      return  (int) node.item <= (int) temp.item;
    }

    /**
     * Checks for the values of the node and inserts accordingly.
     * @param node is the new value.
     * @param temp is the current looked on item.
     * */
    private void orderInsert(Node<Item> node, Node<Item> temp){
        boolean flag = false;
        while (temp != first){
            if(nodeCompare(node,temp)){
                node.next = temp.next;
                temp.next.prev = node;
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
    /**
     * Checks if the node should be head of the queue or not.
     * */
    private void orderHeadofQueue(Node<Item> node, Node<Item> temp){
        if((nodeCompare(temp,node))){
            node.next = temp;
            temp.prev = node;
            first = node;
            node.prev = last;
        }
        else {
            node.next = temp.next;
            temp.next.prev = node;
            temp.next = node;
            node.prev = temp;
        }
    }
    /**
     * Set the last to a new node.
     * */
    private void setLast(Node node){
        last = node;
        first = node;
        last.next = first;
        last.prev = first;
    }
    /**
     * Prints the queue in reverse
     * */
    private void printQueue(){
        int count = 0;
        Node node = last;
        if(stackSize == 1){
            System.out.println("["+node.item+"] ");
        }
        else {
            while (count < stackSize) {
                System.out.print("[" + node.item + "] ");

                node = node.prev;
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
    /**
     * This method serves as the start method for the virtual machine and contains a random integer
     * generator that throws different numbers into the list.
     * @param args contains an array of string given by the user in cmd.
     * */
    public static void main(String[] args){
        long startTime;
        long endTime;
        double[] time = new double[5];
        double runTime = 0;
        OrderdQueue<Integer> orderQueue = new OrderdQueue<>();

        Random random = new Random();

        for(int i =0; i<5; i++){
            startTime = System.nanoTime();
            orderQueue.enterQueue(random.nextInt(10));
            endTime = System.nanoTime();
            time[i] = (endTime - startTime)/(1*Math.pow(10,9));
        }

        for(double d: time){
            runTime += d;
        }
        runTime = runTime/5;

        orderQueue.deenterQueue();

        System.out.print("Mean runtime is : " + runTime);


    }
}
