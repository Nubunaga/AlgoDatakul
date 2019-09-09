import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This test is implemented to check if the index system work and removing of the index node.
 * @author Netanel Avraham Eklind
 * @version 1: 2019-09/09
 *
 * */
public class FIFOTest {
    FIFO fifo;

    @Before
    public void setUp() throws Exception {
        fifo = new FIFO();
    }

    @After
    public void tearDown() throws Exception {
        fifo = null;
    }

    @Test
    public void enterQueue() {
        fifo.enterQueue(5);
        fifo.enterQueue(4);
        fifo.enterQueue(3);
        fifo.enterQueue(1);
        Assert.assertTrue("There is not a correct indexing",fifo.getIndex() == 4);
    }

    @Test
    public void removeKthNode() {
        fifo.enterQueue(5);
        fifo.enterQueue(4);
        fifo.enterQueue(3);
        fifo.enterQueue(1);
        Assert.assertTrue("The index item to remove is not correct",
                (int)fifo.removeKthNode(4)== 5);
    }
}
