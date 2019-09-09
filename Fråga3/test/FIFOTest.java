import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * This test build is to test the FIFO ADT on question 3 and prove that the code, does exactly
 * as shown in the running of the program after test phase.
 * @author Netanel Avraham Eklind
 * @version 1 2019-09/09: implement test code.
 * Test passed in 8 ms.
 * */
public class FIFOTest {

    FIFO fifotest;

    @Before
    public void setUp() throws Exception {
        fifotest = new FIFO();
    }

    @After
    public void tearDown() throws Exception {
        fifotest = null;
    }

    @Test
    public void enterQueue() {
        fifotest.enterQueue(5);
        fifotest.enterQueue(7);
        fifotest.enterQueue(6);
        fifotest.enterQueue(3);
        Assert.assertTrue("There is a new linked list",fifotest.queueSize() > 0);
        Assert.assertTrue("There is correct items in the stack", fifotest.getLastItem().equals(3));
    }

    @Test
    public void deenterQueue() {
        fifotest.enterQueue(5);
        fifotest.enterQueue(7);
        fifotest.enterQueue(6);
        fifotest.enterQueue(3);
        Assert.assertTrue("The correct item has not been removed", 3 == (int) fifotest.deenterQueue());
    }

    @Test
    public void queueSize() {
        fifotest.enterQueue(5);
        fifotest.enterQueue(7);
        fifotest.enterQueue(6);
        fifotest.enterQueue(3);
        Assert.assertTrue("The size does not match the amount pressent", fifotest.queueSize() == 4);
    }

    @Test
    public void isEmptyLast() {
      Assert.assertTrue("There is an item or not in this node",fifotest.isEmptyLast());
        fifotest.enterQueue(5);
        fifotest.enterQueue(7);
        fifotest.enterQueue(6);
        fifotest.enterQueue(3);
        Assert.assertFalse("There is no item in the last node",fifotest.isEmptyLast());
    }

    @Test
    public void isEmptyFirst() {
        Assert.assertTrue("There is an item or not in this node",fifotest.isEmptyFirst());
        fifotest.enterQueue(5);
        fifotest.enterQueue(7);
        fifotest.enterQueue(6);
        fifotest.enterQueue(3);
        Assert.assertFalse("There is no item in the last node",fifotest.isEmptyFirst());
    }
}
