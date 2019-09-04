/**
 * This class purpouse is to handle a double linked circular list in java.
 * This by taking the node constructor from the previus stack and implement it in a way to handle this situation.
 * This by using the same methods as before but modified to handle the current system, ie <em> push </em> and
 * <em> pop </em>
 *
 * @author Netanel Avraham Eklind
 * @version 1 - 02/09-2019
 * @param Item      generic type value.
 * */

/* public class CircularList<Item> {
    private FundamentalsLabb.Stack start;
    private FundamentalsLabb.Stack end;
    private int stackSize;
    /**
     * constructor for the circular list.
     * *
    public CircularList(){
        start = null;
        end = null;
        stackSize = 0;
    }
    /**
     * Adds an item to the stack list.
     *
     * @param item    a generic type instance of a value
     * *
    public void push(Item item){
        FundamentalsLabb.Stack node = new FundamentalsLabb.Stack();
        if(isEmpty()){
            node.push(item);
            start = node;
            end = start;
        }
        else{
            node.push(item);
            end.setNext(node.getNode());
            start = node;
        }

    }

    private boolean isEmpty(){
        return start == null;
    }
}
*/
