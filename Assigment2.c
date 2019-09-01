# include <stdio.h>
/**
 * This code were implemented to solve the riddle on how stdin and stdout works on C. 
 * This is a good exaple of an "end of filé use of the program and how to implement it correctly.
 * this works by setting a while loop untill the condition is found that it is an end of file
 * EOF, untill then each new press or file will print out a new char to stdout.
 * 
 * @author Netanel Avraham Eklind
 * @verision 1 29/8-2019.
 * 
*/

void main(){
char c;

c = getchar();
while (c != EOF){
    if(c == 'a'){
        putchar('x');
    }
    else
    {
        putchar(c);
    }
    c = getchar();
}
}