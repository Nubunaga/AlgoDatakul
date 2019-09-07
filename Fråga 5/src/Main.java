

/**
 * This main will be there to help the user check if the generic circular
 * double linked list. The QUEUE list will be used from the last questions.
 * @author Netanel Avraham Eklind
 * */

public class Main {
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
    }
}
