import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Test the program for checking file.
 * @author Netanel Avraham Eklind
 * @version  1: 2019-09/09
 * */
public class FilterParenthesesTest {
    FilterParentheses filterParentheses;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        filterParentheses = null;
    }

    @Test
    public void readFile()throws IOException {
        FileReader fileReader = new FileReader(
                "C:\\Users\\tomas\\Desktop\\AlgoData\\Fråga 7\\src\\FIFOFråga7test.java");
        filterParentheses = new FilterParentheses(fileReader);
        filterParentheses.readFile();
        Assert.assertTrue("The file is not balanced",filterParentheses.checkIfBalanced());
        FileReader fileReader1 = new FileReader(
                "C:\\Users\\tomas\\Desktop\\AlgoData\\Fråga 7\\src\\FIFOFråga7Test2.java");
        filterParentheses.setNewFileReader(fileReader1);
        filterParentheses.readFile();
        Assert.assertFalse("The file is balanced",filterParentheses.checkIfBalanced());

    }
}
