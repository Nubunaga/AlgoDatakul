import java.util.Scanner;
/**
 * This class design is to implement a file reader from that takes values to <em> Stdin </em>
 * and prints them to <em>stdout</em> with the pipeline technique utilizing the phrase in cmd
 * <code> java AssigmentThree.java < assigment3file.txt</code>.
 *
 * The class completes a task by taking the file and takes the values from it, placing them
 * inside the array, when the array is full, the other <code> for loop</code> prints out the
 * array in reverse order.
 *
 * @version 1 29/8 - 2019
 * @author Netanel Avraham Eklind
 *
 * */
public class AssigmentThree {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int nrElements = in.nextInt();
        int[] array = new int[nrElements];
        int i;

        for( i = 0; i < array.length;i++){
            array[i] = in.nextInt();
        }

        for (i = array.length-1; i >= 0; i--){
            System.out.println(array[i]);
        }
    }
}
