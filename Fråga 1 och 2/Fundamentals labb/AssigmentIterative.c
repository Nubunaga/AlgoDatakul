#include <stdio.h>
#include <stdlib.h>

/**
 *************************************************
 * @author Netanel Avraham Eklind
 * @verision 1 2019-09-03 - implementing code.
 *************************************************
 * The following code test the usage of the iterative printing of
 * an array of chars from a textfile using pipelining or using 
 * keyboard stream. Using this method does not affect the stack and thus
 * reduce memmory usage of the code. But at the same time increase the 
 * time complexity for the exection.
 * This code uses <em> stdin </em> and <em> stdout </em> per usual standard.
 * 
*/

/**
 * This function is called to store value in an array using dynamic
 * allocated memmory or <code> malloc </code> to save the char input.
 * Saving the data in an array and then print it in reverse.
*/
void printIterative(){
    char *p = malloc(30*sizeof(char*));
    int i = 0;
    p[0] = getchar();
    while(p[i] != '\n'){
        i++;
        p[i] = getchar();
    }
    while(i >= 0){
        putchar(p[i]);
        i--;
    }
    free(p);
    
}
/**
 * Runs the program and takes the input.
*/
void main(){
printIterative();
}