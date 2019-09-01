
/**
 * Timeline(
 * @version 1 30/8 -  2019
 * )
 * @author Netanel Avraham Eklind
 * The code usage is to initilize <code> {@link Stack}</code> class and test the
 * fetures that is pressent within them, this being the <em> push </em>, <em> pop</em>
 * and other fetures that could be added to a different day. This file will be the main
 * for that class but will not replace unit-testing.
 * */

public class AssigmentFourMain {
    public static void main(String [] args){
        Stack first = new Stack();


        first.push(12);
        first.push(13);
        first.push(25);
        first.push(54);
        first.push(87);

        System.out.println(first);
        System.out.println(first.pop());
        System.out.println(first);

        first.insertElement(16,25);

        System.out.println(first);

        first.priorityInsert(15);
        System.out.println(first);



    }
}
