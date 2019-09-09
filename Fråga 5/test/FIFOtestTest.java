import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test is to test the other FIFO program that has a nested index class
 * in it and see if it works completely.
 * */
public class FIFOtestTest {
    TestofFIFOIndex fifOtest ;

    @Before
    public void setUp() throws Exception {
        fifOtest = new TestofFIFOIndex();
    }

    @After
    public void tearDown() throws Exception {
        fifOtest = null;
    }

    @Test
    public void enterQueue() {
        TestofFIFOIndex.Index arr[] = new TestofFIFOIndex.Index[4];
        arr[0] = fifOtest.enterQueue(5);
        arr[1] = fifOtest.enterQueue(3);
        arr[2] = fifOtest.enterQueue(4);
        arr[3] = fifOtest.enterQueue(7);
        Assert.assertTrue("There is an increase in indexing",fifOtest.getIndex() > 0);
    }

    @Test
    public void removeKthNode() {
        TestofFIFOIndex.Index arr[] = new TestofFIFOIndex.Index[4];
        arr[0] = fifOtest.enterQueue(5);
        arr[1] = fifOtest.enterQueue(3);
        arr[2] = fifOtest.enterQueue(4);
        arr[3] = fifOtest.enterQueue(7);
        Assert.assertTrue("the right element has been removed",(int)fifOtest.removeKthNode(arr[2])== 4);
    }
}
