import java.util.Random;

/**
 * This main is for Question 6 in the fundamentals lab
 * <a href:https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 * question 6</a>. It will be used to create an object of ordered file and then use different integer
 * values to input and order the integer in acceding order. This will be done by looking for the lowest
 * element and sort it.
 * The main class also implement <code>java.util.Random</code> to throw in different
 * numbers to make sure that the algorithm handles each new value correctly.
 * Also a time measurement is implemented as <code> System.nanoTime(); </code>
 * that takes the internal system time and then takes the <em> "mean"</em> from all the times
 * the queue sorting algorithm is used and checks the runtime for that.
 *
 * execution time : 0.0044 - 0.0052 seconds depending on the order the numbers are thrown into the system.
 * best case : constant time.
 * worst case: n time.
 * @author Netanel Avraham Eklind
 * @version 1: 2019-04/09: implementing code
 * */

public class Main {
    public static void main(String[] args)throws InterruptedException{
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

        System.out.print("Mean runtime is : " + runTime);


    }
}
