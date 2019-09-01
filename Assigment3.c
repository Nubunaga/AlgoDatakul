#include <stdio.h>
/**
 * This code generates a array from a file or input and presents it in the reverse order.
 * This to learn how to handle the code itselfe and also to think how the stdin and stdout
 * can be used in the programing world.
 * 
 * @author netanel Avraham Eklind
 * @verision 1 29/8-2019.
 * 
 * no test where made on this code since the main can sufficently provide us with correct result.
 * 
 * 
*/
void main(){

    int nr
    
    Elements;

    scanf("%d",&nrElements);

    int array [nrElements];
    int i;
    for(i = 0; i < nrElements; i++){
        scanf("%d",&array[i]);
    }

    for (i = nrElements-1;i>=0;i--){
        printf("%d\n",array[i]);
    }

}