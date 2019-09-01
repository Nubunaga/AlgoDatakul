import java.util.Scanner;
/**
 * The code presented here solves the problem with understanding <em> stdin </em> and
 * <em> stdout </em>. The code solves this by taking the s
 * @author Netanel Avraham Eklind
 * @version 1 29/8-2019
 * */
public class AssigmentTwo {
    public static void main(String[]args)throws Exception{
       Scanner in = new Scanner(System.in);

       String c = in.next();
        int j = 0;
       char[] chat = c.toCharArray();

       while(true){

           for (int i = 0; i < c.length(); i++){
               if(chat[i] == 'a'){
                   System.out.println('x');
               }
               else{
                   System.out.println(chat[i]);
               }
               j++;
           }
           if(chat[j] == '-'){
               break;
           }
           c = in.next();
           chat = c.toCharArray();
       }
    }
}
