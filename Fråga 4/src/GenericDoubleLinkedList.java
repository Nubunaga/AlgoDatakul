import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
/**
 * This is a build of the generic valued iterable single linked list with the function of
 * taking the value <em> " pop " </em> the head or the first element in the list
 * or the last element in the list.
 * This design is based on the <code> Stack </code> ADT from previous assignment,
 * <a href: https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 *     question 2</a>, whom code got inspired by the code from
 *     <a href: https://algs4.cs.princeton.edu/13stacks/> </a> Algorithms 4:th Edition, 1.3.
 *
 * The program utilises a basic version of the <em> "Strategy pattern" </em> where it chooses which
 * parameter, argument, is sent to the method <code> pop </code>.
 *
 * @author Netanel Avraham Eklind
 * @version  2: 2019-07/09- totally remade, since the code was not correct last time.
 * @param Item is the generic value the class handles.
 * Total runtime of program: 0.036 seconds
 * */
public class GenericDoubleLinkedList<Item> implements Iterable<Item>{

        private int stackSize;
        private Node<Item> first;
        private Node<Item> last;

        private class Node<Item>{
            private Item item;
            private Node<Item> next;
        }
        /**
         * Constructor for the circle double linked list stack.
         * */
        public GenericDoubleLinkedList(){
            first = null;
            last = null;
            stackSize = 0;
        }

        /**
         * Adds a new element to the list
         * @param item generic value of the input.
         * */
        public void push(Item item){
            if(isEmpty()){
                first = new Node<>();
                first.item = item;
                last = first;
                last.next = first;
            }
            else{
                insertNode(item);
            }
                stackSize++;
                printQueue();
        }
        /**
         * Implements basic strategy pattern by choosing on command
         * witch algorithm to use.
         * @param node is the name for the node to remove or "pop".
         * @throws NoSuchElementException if there is no element in that spot.
         * */
        public Item pop(String node){
            if(isEmpty())throw new NoSuchElementException("No element found");
            switch (node){
                case "First":
                    printQueue();
                    return popFirst();
                case "Last":
                    printQueue();
                    return popLast();

                    default:
                        return null;
            }
        }
        /**
         * Checks if first "head" is empty or not
         * @return true if this is the case or false if it is not.
         * */
        public boolean isEmpty(){
            return first == null;
        }

        private void printQueue(){
            int count = 0;
        for(Item i: this){
            if(count >= stackSize){
                break;
            }
            System.out.print(" ["+i+"] ");
            if(stackSize >= 1)System.out.print(",");
            count++;
        }
        System.out.print("\n");
    }

        private void insertNode(Item item){
            Node oldNode = first;
            first = new Node<>();
            first.item = item;
            first.next = oldNode;
            last.next = first;
        }

        private Item popFirst(){
            Item item = first.item;
            first = first.next;
            last.next = first;
            stackSize--;
            return item;
        }

        private Item popLast(){
            Node<Item> temp = first;
            while(temp.next != first){
                temp = temp.next;
            }
            Item item = temp.next.item;
            temp.next = first;
            stackSize--;
            return item;
        }

        /**
         * returns the new iterator item to the first item and then initializes the for
         * each loop.
         * @return Iterator<Item> as the first node.
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
         * @throws NoSuchElementException if no element is found
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
     * The main method that runs the program, it can both take the input from the user and implement it
     * accordingly, choosing a strategy.
     *
     * @param args a string array containing the argument from the user input.
     *
     * */
    public static void main(String [] args){
        GenericDoubleLinkedList genericDoubleLinkedList = new GenericDoubleLinkedList();
        long startTime;
        long endTime;
        double [] time = new double[5];
        double runtime = 0;
        double totalRuntime;
        Random random = new Random();

        for(int i =0; i<5; i++){
            startTime = System.nanoTime();
            genericDoubleLinkedList.push(random.nextInt(10));
            endTime = System.nanoTime();
            time[i] = (endTime - startTime)/(1*Math.pow(10,9));
        }
        for(double d: time){
            runtime += d;
        }
        System.out.print("The push algorithm goes on: "+ runtime +"\n");

        totalRuntime =+ runtime;
        runtime = 0;

        for(int i =0; i<5; i++){
            startTime = System.nanoTime();
            genericDoubleLinkedList.pop("First");
            genericDoubleLinkedList.pop("Last");
            endTime = System.nanoTime();
            time[i] = (endTime - startTime)/(1*Math.pow(10,9));
        }

        for(double d: time){
            runtime += d;
        }

        System.out.print("\n The pop algorithm goes on: " + runtime);

        totalRuntime += runtime;

        System.out.print("\n Total runtime is: "+ totalRuntime);
    }
}
