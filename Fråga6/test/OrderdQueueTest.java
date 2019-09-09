import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderdQueueTest {
    OrderdQueue orderdQueue;

    @Before
    public void setUp() throws Exception {
        orderdQueue = new OrderdQueue();
    }

    @After
    public void tearDown() throws Exception {
        orderdQueue = null;
    }

    @Test
    public void enterQueue() {
        orderdQueue.enterQueue(4);
        orderdQueue.enterQueue(4);
        orderdQueue.enterQueue(8);
        orderdQueue.enterQueue(9);
        orderdQueue.enterQueue(6);
        Assert.assertTrue("not the lowest node", (int)orderdQueue.getItem() == 9 );
    }
}

