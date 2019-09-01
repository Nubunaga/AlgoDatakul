import org.junit.Assert;

public class StackTest {
    Stack first;

    @org.junit.Before
    public void setUp() throws Exception {
        first = new Stack();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        first = null;
    }

    @org.junit.Test
    public void push() {
        first.push(21);
        Assert.assertNotEquals(0,first.getSize());
    }

    @org.junit.Test
    public void pop() {
        first.push(21);
        Assert.assertTrue((int)first.pop() == 21);
    }

    @org.junit.Test
    public void getSize() {
        first.push(21);
        Assert.assertTrue(first.getSize() == 1);
    }

    @org.junit.Test
    public void insertElement() {
        first.push(21);
        first.push(26);
        first.insertElement(23,21);
        Assert.assertTrue(first.getSize() == 3);
    }

}