import java.io.FileReader;
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
 * runtime: 0.0175 - 0.015 seconds.
 * */

public class FilterParentheses<Item> {
    private Integer i ;
    private Node<Item> first;
    private boolean balanced;
    private int parentheses;
    private boolean flag = true;
    private FileReader fileReader;
    /**
     * Constructor for the node
     * */
    private class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    /**
     * Constructor for the <code> {@link FilterParentheses} </code> object
     * containing the values in <em> integer </em> that will keep track
     * on the current parentheses.
     * @param fr is a <code> {@link FileReader}</code> object that contains
     *           reference to a file.
     *
     */
    public FilterParentheses(FileReader fr){
       first = null;
       balanced = false;
       parentheses = 0;
       fileReader = fr;
    }
    /**
     * Resets the last session for a new one.
     * @param fr is a <code> {@link FileReader}</code> object that contains
     *      *           reference to a file.
     * */
    public void setNewFileReader(FileReader fr){
        fileReader = fr;
        balanced = false;
        first = null;
        parentheses = 0;
        flag = true;
    }

    /**
     * Reads the characters from the file and counts them.
     * @throws IOException for the input stream.
     * */
    public void readFile()throws IOException {
         i = fileReader.read();
        if(i == 34){
            stringChecker();
        }
        if (i != -1 && flag){
            countParentheses(i);
            readFile();
            }
        if(i != -1){
            readFile();
        }
    }
    /**
     * Checks if there is a start of the string or an end.
     * */
    private void stringChecker() {
        if(flag){
            flag = false;
        }
        else{
            flag = true;
        }
    }
    /**
     * Pushes the item to the current node.
     * @param item a generic value.
     * */
    private void push(Item item){
        if(isEmpty()){
            first = new Node<>();
            first.item = item;
        }
        else{
            insertNode(item);
        }
    }
    /**
     * pops,removes, the current node.
     * */
    private void pop()throws IOException{
        try {
        if (isEmpty())throw new NoSuchElementException("No element found");
        first = first.next;
        if(isEmptyList()){
            balanced = true;
        }
        }
        catch (NoSuchElementException e){
            readFile();
            balanced = false;
        }
    }
    /**
     * checks if the list is empty.
     * @return true if this is the case and false otherwise.
     * */
    private boolean isEmptyList() {
        try {
            Node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            return false;
        }catch (NullPointerException e){
            return true;
        }

    }
    /**
     * insert a new node into the system
     * @param item is the generic value.
     * */
    private void insertNode(Item item){
        Node<Item> oldNode = first;
        first = new Node<>();
        first.item = item;
        first.next = oldNode;
    }

    /**
     * Checks for empty node, first.
     * */
    private boolean isEmpty(){
        return first == null;
    }
    /**
     * This methods checks for the parentheses and checks if they should be pushed, poped
     * or count the amount. This is the main algorithm for the program.
     * @param c contains the integer value of the char.
     * @throws IOException if there is a problem with the IO.
     * */
    private void countParentheses(Integer c) throws IOException {
            if (openParentheses(c)) {
                parentheses++;
                push((Item) c);
            } else if (closedParentheses(c)) {
                try {
                parentheses++;
                if ((c - (int) first.item <= 2 && !(c - (int) first.item <= 0))) {
                    pop();
                } else{
                    balanced = false;
                    push((Item)c);
                }
                }
                catch (NullPointerException e){
                    balanced = false;
                }
            }

    }
    /**
     * Checks if the file is balanced.
     * @return true or falce depending on<code> balanced </code> has been set.
     * */
    public boolean checkIfBalanced(){
       if(parentheses == 0){
           System.out.print("\n no parentheses were found in the text");
           return balanced;
       }
       else {
           System.out.println("A total of: "+ parentheses);
           return balanced;
       }
    }
    /**
     * Checks if a character is one of the 3 parentheses, open ones.
     * */
    private boolean openParentheses(Integer c){
        return c == '(' || c == '[' || c == '{';
    }
    /**
     * Checks if a character is one of the 3 parentheses, closed ones.
     * */
    private boolean closedParentheses(Integer c){
        return (c == ')' || c == ']' || c == '}') & !isEmpty();
    }

    /**
     * Starts the program and reads the file into the system with a <code> {@link FileReader} </code>
     * that takes the file into the system. One can also use the commands " java class.java test.txt"
     * replacing the necessary class to thr right one and text to analyze to get same result.
     * */
    public static void main(String[] args)throws IOException{
        long startTime;
        long endTime;
        double runTime = 0;
        int i = System.in.read();
        if( i == 't') {
            startTime = System.nanoTime();
            FileReader fr = new FileReader("src/FIFOFråga7test.java");

            FilterParentheses filterParentheses = new FilterParentheses(fr);
            filterParentheses.readFile();

            System.out.print("\n The code is parentheses balanced: "
                    + filterParentheses.checkIfBalanced());
            endTime = System.nanoTime();
            runTime = (endTime - startTime) / (1 * Math.pow(10, 9));
            System.out.print("\n" + "runtime: " + runTime + "\n");


            FileReader fr1 = new FileReader("src\\FIFOFråga7Test2.java");

            filterParentheses.setNewFileReader(fr1);
            filterParentheses.readFile();
            System.out.print("\n The code is parentheses balanced: "
                    + filterParentheses.checkIfBalanced());
        }
        else{
            startTime = System.nanoTime();
            FileReader fr = new FileReader(args[0]);

            FilterParentheses filterParentheses = new FilterParentheses(fr);
            filterParentheses.readFile();

            System.out.print("\n The code is parentheses balanced: "
                    + filterParentheses.checkIfBalanced());
            endTime = System.nanoTime();
            runTime = (endTime - startTime) / (1 * Math.pow(10, 9));
            System.out.print("\n" + "runtime: " + runTime + "\n");

        }
    }
}


