import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This test class is designed to test the pop function from the class
 * <code> {@link GenericDoubleLinkedList}</code>, this is because
 * the push function is the same this time as in the second question
 * meaning it has been tested before hand.
 * But one test can be made to just check if it still workds
 * @author Netanel Avraham Eklind
 * @version 1: 2019-09/09
 * Test runtime: 46 ms
 * */
public class GenericDoubleLinkedListTest {
    GenericDoubleLinkedList genericDoubleLinkedList;

    @Before
    public void setUp() throws Exception {
        genericDoubleLinkedList = new GenericDoubleLinkedList();
    }

    @After
    public void tearDown() throws Exception {
        genericDoubleLinkedList = null;
    }
    @Test
    public void push(){
        genericDoubleLinkedList.push(5,"First");
        genericDoubleLinkedList.push(3,"First");
        genericDoubleLinkedList.push(7,"Last");
        genericDoubleLinkedList.push(9,"Last");
        Assert.assertTrue("there is an increase in the list",
                genericDoubleLinkedList.getStackSize() == 4);
        genericDoubleLinkedList.push(null,"Last");
        Assert.assertTrue("The list can handle null",genericDoubleLinkedList.getStackSize() == 5);
    }

    @Test
    public void popLast() {
        genericDoubleLinkedList.push(5,"First");
        genericDoubleLinkedList.push(3,"First");
        genericDoubleLinkedList.push(7,"First");
        genericDoubleLinkedList.push(9,"First");
        Assert.assertTrue("The last item has not been removed",
                5 == (int)genericDoubleLinkedList.pop("Last"));
    }

    @Test
    public void popFirst(){
        genericDoubleLinkedList.push(5,"First");
        genericDoubleLinkedList.push(3,"First");
        genericDoubleLinkedList.push(7,"First");
        genericDoubleLinkedList.push(9,"First");

        Assert.assertTrue("The first item has not been reoved",
                9 == (int)genericDoubleLinkedList.pop("First"));
    }
    @Test
    public void popNull(){
        genericDoubleLinkedList.push(5,"First");
        genericDoubleLinkedList.push(3,"First");
        genericDoubleLinkedList.push(7,"First");
        genericDoubleLinkedList.push(9,"First");
        Assert.assertTrue("The input can't handle wrong input",
                null == genericDoubleLinkedList.pop("bla"));
    }
}
