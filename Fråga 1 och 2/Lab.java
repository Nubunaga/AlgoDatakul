package FundamentalsLabb;


import java.io.IOException;
import java.util.Scanner;

/**
 * @author Netanel Avraham Eklind
 * <p>
 * This program implements the <code> {@link Stack} </code> from previous lab
 * to use for a recursive program print of the value sent from the user. This
 * will thus solve the problem with an artificial stack that will print the
 * stack in reverse. The recursive method will <em> push </em> a new char each time it
 * is called and pop to reverse print the method.
 * <p>
 * with inspiration from
 * * <a href = https://algs4.cs.princeton.edu/13stacks/> sektion 1.3 </a>
 * * <i> Algorithms, 4th Edition </i>
 * and code reused from preparatory <i> FundamentalsLabb.Stack </i>.
 * @version 2 04/09-2019: finish the code
 */

public class Lab {
    private Stack stack;
    private int counter = 0;
    char c1;
    Scanner in = new Scanner(System.in);

    public Lab() {
        stack = new Stack();
    }

    /**
     * A recursive method witch utilize the stack to store each
     * char in a node
     *
     * @throws IOException to handle if a problem arises from the stdin.
     */
    public void recursivePrint() throws IOException {
        c1 = (char) System.in.read();
        if (c1 != '\n') {
            stack.push(c1);
            recursivePrint();
            counter++;
        }
        if (counter > 0) {
            System.out.print(stack.pop());
        }
    }

    /**
     * A iterative method to gather the chars from the input and
     * print them out in reverse.
     *
     * @throws IOException to handle if a problem arises from the stdin.
     */
    public void iterativePrint() throws IOException {
        char c[] = new char[50];
        c1 = (char) System.in.read();
        counter = 0;
        while (c1 != '\n') {
            c[counter] = c1;
            c1 = (char) System.in.read();
            counter++;
        }
        while (counter > 0) {
            System.out.print(c[counter]);
            counter--;
        }
    }

    public static void main(String[] args) throws IOException {

        Lab lab2 = new Lab();

        System.out.println(" ");
        lab2.recursivePrint();
        lab2.iterativePrint();
    }
}

