import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * The code class <code> {@link FilterParentheses} </code> will check a string
 * from <em> stdin </em> for the <i> "Balance parentheses problem "</i> meaning,
 * it will look for closing parentheses in the string if one has been properly
 * closed with another parentheses.
 *
 * The arguments that will be looking for is <em> " (,[, {" </em> and look if they
 * close in any part of the program.This is done by creating a linked list, whenever
 * one of the open parentheses is detected. It creates one and wait until it finds
 * another open one, increasing the link, or find a closing one. And thus <code> pop
 * </code> the latest one.
 *
 * @author Netanel Avraham Eklind
 * @version 1: 2019-06/09 - implement code.
 * Questions from where the problem derives from:
 * <a href: https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 *     question 7 </a>
 * runtime: 0.0175 seconds.
 * */

public class FilterParentheses<Item> {
    private Integer i ;
    private Node<Item> first;
    private boolean balanced;
    int parentheses;

    private class Node<Item>{
        private Item item;
        private Integer cousin;
        private Node<Item> next;
    }

    /**
     * Constructor for the <code> {@link FilterParentheses} </code> object
     * containing the values in <em> integer </em> that will keep track
     * on the current parentheses.
     */
    public FilterParentheses(){
       first = null;
       balanced = false;
       parentheses = 0;

    }

    /**
     * Reads the characters from the file and counts them.
     * @throws IOException for the input stream.
     * */
    public void readFile()throws IOException {
             i = System.in.read();
        if (i != -1){
            countParentheses(i);
            readFile();
            }
    }

    private void push(Item item){
        if(isEmpty()){
            first = new Node<>();
            first.item = item;
        }
        else{
            insertNode(item);
        }
    }
    private void pop()throws IOException{
        try {
        if (isEmpty())throw new NoSuchElementException("No element found");
        first = first.next;
        if(isEmpty()){
            balanced = true;
        }
        }
        catch (NoSuchElementException e){
            readFile();
            balanced = false;
        }
    }

    private void insertNode(Item item){
        Node<Item> oldNode = first;
        first = new Node<>();
        first.item = item;
        first.next = oldNode;
    }


    private boolean isEmpty(){
        return first == null;
    }
    private void countParentheses(Integer c) throws IOException {
        if(openParentheses(c)){
            parentheses++;
            push((Item) c);
        }
        else if (closedParentheses(c)){
            parentheses++;
            if(c - (int) first.item <= 2 && !(c - (int) first.item <= 0)) {
                pop();
            } else balanced = false;
        }
    }
    private boolean checkIfBalanced(){
       if(parentheses == 0){
           System.out.print("\n no parentheses were found in the text");
           return balanced;
       }
       else {
           System.out.println("A total of: "+ parentheses);
           return balanced;
       }
    }

    private boolean openParentheses(Integer c){
        return c == '(' || c == '[' || c == '{';
    }

    private boolean closedParentheses(Integer c){
        return c == ')' || c == ']' || c == '}';
    }

    /**
     * Starts the program and reads the file into the system with commands <code>
     *     java FilterParentheses.java < text.txt
     * </code>
     * */
    public static void main(String[] args)throws IOException{
        long startTime;
        long endTime;
        double runTime = 0;
        startTime = System.nanoTime();
        FilterParentheses filterParentheses = new FilterParentheses();
        filterParentheses.readFile();
        System.out.print("\n The code is parentheses balanced: "
                + filterParentheses.checkIfBalanced());
        endTime = System.nanoTime();
        runTime = (endTime-startTime)/(1*Math.pow(10,9));
        System.out.print("\n"+ "runtime: "+runTime);
    }
}


