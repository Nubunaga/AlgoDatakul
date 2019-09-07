import java.io.IOException;

/**
 * The code class <code> {@link FilterParentheses} </code> will check a string
 * from <em> stdin </em> for the <i> "Balance parentheses problem "</i> meaning,
 * it will look for closing parentheses in the string if one has been properly
 * closed with another parentheses.
 *
 * The arguments that will be looking for is <em> " (,[, {" </em> and look if they
 * close in any part of the program.
 *
 * @author Netanel Avraham Eklind
 * @version 1: 2019-06/09 - implement code.
 * Questions from where the problem derives from:
 * <a href: https://kth.instructure.com/courses/12734/pages/the-fundamentals-labpm?module_item_id=137881>
 *     question 7 </a>
 * runtime: 0.0026 seconds.
 * */

public class FilterParentheses {
    private int braces;
    private int brackets;
    private int parantheses;
    private char c;
    private int i ;

    /**
     * Constructor for the <code> {@link FilterParentheses} </code> object
     * containing the values in <em> integer </em> that will keep track
     * on the current parentheses.
     */
    public FilterParentheses(){
        braces = 0;
        brackets = 0;
        parantheses = 0;
    }

    /**
     * Reads the characters from the file and counts them.
     * @throws IOException for the input stream.
     * */
    public void readFile()throws IOException {
            i = System.in.read();
        if (i != -1){
            c = (char) i;
            countParentheses(c);
            readFile();
            }
    }
    private void countParentheses(char c){
        switch (c){
            case '(':
                parantheses++;
                break;
            case '[':
                brackets ++;
                break;
            case '{':
                braces ++;
                break;
            case')':
                parantheses--;
                break;
            case ']':
                brackets --;
                break;
            case '}':
                braces --;
            default:
                break;
        }
    }
    private boolean checkIfBalanced(){
        return (braces == 0 && brackets == 0 && parantheses == 0);
    }

    /**
     * Starts the program and reads the file into the system with commands <code>
     *     java FilterParentheses.java < text.txt
     * </code>
     * */
    public static void main(String[] args)throws IOException{
        long startTime;
        long endTime;
        double runTime = 0;
        startTime = System.nanoTime();
        FilterParentheses filterParentheses = new FilterParentheses();
        filterParentheses.readFile();
        System.out.print("The code is parentheses balanced: ");
        System.out.print(filterParentheses.checkIfBalanced());
        endTime = System.nanoTime();
        runTime = (endTime-startTime)/(1*Math.pow(10,9));
        System.out.print("\n"+ "runtime: "+runTime);
    }
}


