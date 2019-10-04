import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Compilation: javac BinarySearchST.java
 * Execution:java java BinarySearchST.java
 * Dependencies: None
 * Input: TheTextOne.txt
 * Output:
 *#########################################################################
 *
 *------------------------------------------------------------------------
 * Binary search ST
 *
 * Chapter 45
 * distinct = 249
 * words    = 500
 * With minimum length: 1 and words to look at: 500 total runtime for
 * put is: 6.3424069999999855ns and get:
 * 6.260043ns
 *------------------------------------------------------------------------
 *
 *########################################################################
 * Question to answer:
 * Use the first N (N in the order of hundred words) words from the text
 * to compare the running times of the ordered array ST (algorithm 3.2)
 * to the Binary Search Tree algorithm (Algorithm 3.3) (you need only
 * implement the basic operations to put and get keys to/from the ST)
 * Use the FrequencyCounter from page 372 as test program (you may need
 * to change how you read the words if you do not use the libraries from
 * Sedgewick&Wayne)..
 *########################################################################
 *
 * This code will implement the <em> Binary Search Tree </em> "BTS" from
 * Algorithm 4th edition and build on it to solve the given assignment.
 * The program should run through a N amount of words and show the word that
 * have been used the most and print it. Also show the time for the program to
 * execute a put and get algorithm.
 *
 * @author Netanel Avraham Eklind
 * @version 1: 2019 -24/09
 * With inspiration from
 * <a href: Algorithm 4th edition> Chapter 3 section 1, Algorithm 3.2</a>
 * */

public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    /**
     * Constructor to set basic size of symbol table.
     * */
    public BinarySearchST(){
        this(100);
    }

    /**
     * private constructor containing the symbol table
     * <code> keys </code> contains the key of the table
     * <code> vals </code> contains how many there are.
     * @param capacity contains the size of the arrays.
     * */
    private BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Comparable[capacity];
    }

    /**
     * returns the size of the table.
     * @return the size of the table.
     * */
    public int size(){
        return N;
    }

    /**
     * Gets the searched value from the symbol table and returns it
     * to the user.
     * @param key contains the key to look for.
     * @return <code> null </code> if it is empty else returns
     * <code> vals[i] </code>
     * @throws IllegalArgumentException is there is no keys at all.
     * */
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    /**
     * Checks if table is empty
     * @return true / false.
     * */
    private boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts a new element into the symbol table with a new key.
     * @param key is the value to become the key
     * @param value is the amount this key is used.
     * @throws IllegalArgumentException if there is no key.
     * */
    public void put(Key key, Value value){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        int i = rank(key);

        // key is already in table
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }

        if (N == keys.length) resize(2*keys.length);

        for(int j = N; j > i ;j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * An algorithm that reads a file and adds the words to the symbol table.
     * @param minlen is the minimum length of the word to look for
     * @param capacityWords is how many words to read.
     * @throws FileNotFoundException if no file is found.
     * */
    public void frequencyCounter(int minlen,int capacityWords) throws FileNotFoundException {
        int distinct = 0, words = 0,count = 0;
        double endPut = 0,startTime = 0, endGet = 0;
        BinarySearchST<String,Integer> st = new BinarySearchST<String,Integer>();
        Scanner in = new Scanner(new File("C:\\Users\\tomas\\Desktop" +
                "\\AlgoData\\SearchingLab\\src\\TheTextOne.txt"));

        while(in.hasNext() && count != capacityWords){
            String word = in.next();
            startTime = System.nanoTime();
            if(word.length()< minlen) continue;
            words++;
            if(st.contains(word)) st.put(word,st.get(word)+1);
            else{
                st.put(word,1);
                distinct++;
            }
            count++;
            endPut +=((System.nanoTime() - startTime)/(1*Math.pow(10,6)));
        }
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        startTime = System.nanoTime();
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        endGet =((System.nanoTime() - startTime)/(1*Math.pow(10,6)));
        System.out.println("Binary search ST\n");
        System.out.println(max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);
        System.out.println("With minimum length: "+
                minlen + " and words to look at: "+capacityWords +
                " total runtime for put is: "+ endPut + "ns" +
                " and get: "+ endGet +"ns");
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    /**
     * Return the rank of the program, meaning if it is bigger or lower than another
     * @param key is the key to check for rank.
     * @return the value with highest rank.
     * */
    private int rank(Key key){
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        int lo = 0, hi = N-1 ;
        while(lo <= hi){
            int mid = lo +(hi - lo) /2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)    hi = mid-1;
            else if (cmp > 0)lo = mid +1;
            else return mid;
        }
        return lo;
    }

    private Key min(){
        return keys[0];
    }
    private Key max(){
        return keys[N-1];
    }
    /**
     * Resize the arrays to new size
     * @param capacity is the value for the new array.
     * */
    private void resize(int capacity) {
        assert capacity >= N;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }
    /**
     * Main method for the program.
     * @param args contains an array of strings.
     * */
    public static void main(String[] args)throws FileNotFoundException {
        BinarySearchST searchingLabQTwo = new BinarySearchST();
        int minlen = Integer.parseInt(args[0]);
        int capacityWords = Integer.parseInt(args[1]);

       searchingLabQTwo.frequencyCounter(minlen,capacityWords);
    }

}
