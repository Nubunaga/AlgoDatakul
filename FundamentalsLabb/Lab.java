package FundamentalsLabb;

import java.util.Scanner;

/**
 * @version 1 03/09-2019: implement code
 *
 * @author  Netanel Avraham Eklind
 *
 * This program implements the <code> {@link Stack} </code> from previous lab
 * to use for a recursive program print of the value sent from the user. This
 * will thus solve the problem with an artificial stack that will print the
 * stack in reverse. The recursive method will <em> push </em> a new char each time it
 * is called and pop to reverse print the method.
 *
 * with inspiration from
 *  * <a href = https://algs4.cs.princeton.edu/13stacks/> sektion 1.3 </a>
 *  * <i> Algorithms, 4th Edition </i>
 *  and code reused from preparatory <i> FundamentalsLabb.Stack </i>.
 * */

public class Lab {
    private Stack stack;
    private Scanner in = new Scanner(System.in);
    private int counter = 0;
    private char[] c;
    public Lab(){
        stack = new Stack();
    }

  public  void recursivePrint(){
        if(counter <= 0){
            String s = in.nextLine();
            s += '\n';
            c = s.toCharArray();
        }

        if((c[counter] != '\n')){
            stack.push(c[counter]);
            counter++;
            recursivePrint();
        }
        else {
            System.out.print(stack + " ");
        }
    }

  public   void iterativePrint(){
        String s = in.nextLine();
        s += '\n';
        c = s.toCharArray();
        counter = 0;
        while (c[counter] != '\n'){
            counter ++;
        }
        for(int i = counter -1; i >= 0; i--){
            System.out.print(c[i]);
        }
    }
}

