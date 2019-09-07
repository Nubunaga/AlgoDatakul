/**
 * Question: Implement a generic iterable FIFO-queue based on a double linked list.
 * You should print the content of the list after each insertion/deletion of an element.
 *
 * @author Netanel Avraham Eklind
 * @version 1 2019- 04/09
 *
 * This class is used to test out
 * < a href : https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 * question 3 </>.
 * It will utilize the double circle linked list feature from preparatory, since i did not make it in time,
 * a remake will be needed.
 * */

public class Main {


    public static void main(String[]args) {
        FIFO fifo = new FIFO();


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
        fifo.removeFront();
        fifo.removeLast();
    }

}
