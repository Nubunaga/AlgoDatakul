/*********************************************************
 * @version 1 2019-09-02: The start of the code
 * @version 1 2019-09-03: implement the recursie 
 *                        code.
 * @author Netanel Avraham EKlind
 *********************************************************
 * This code is used to check the usage of a reverse recursion
 * print using the <em> stdin </em> to take the input from the keyboard 
 * or file and <em> stdout </em> prints to screen. By ding this, the 
 * recursive function stores the data in the stack, meaning the function
 * will print it in reverse by how the stack is designed.
 * 
*/

#include <stdio.h>
#include <stdlib.h>
/**
 * Recursive print of char using the stack-placement.
 * @param n contains primitive datatype integer value
 * to handle the save.
*/
void printRecursive(){
    char c;
    c = getchar();
    if(c != EOF){
        printRecursive();
    }
    putchar(c);
}

/**
 * Runs the program and takes the input.
*/
void main(){

printRecursive();

}