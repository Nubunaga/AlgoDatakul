/**
 * Compilation:
 * Execution:
 * Dependencies:
 * Input: TheDatabase.txt
 * ###################################################################
 * Output:
 * -------------------------------------------------------------------
 * <p>
 * -------------------------------------------------------------------
 * ###################################################################
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**This class implements a <I> Symbol Graph</I> that takes <em> string
 * </em> instead of standard graph which take <em> Integer</em>.
 * It keeps an instance of  <em> st </em>,<code> {@link BinarySearchST}
 * </code>
 * that maps names to indices. <em> key </em> which binds indices to
 * names and <em> graph </em> that keeps integers as vertex names.
 *
 * This program will take 2 passes through the graph definition,
 * <code> {@link Graph} </code>, to build the data structure.
 *
 *
 * @author Netanel Avraham Eklind
 * @version 1: 2019 - 10/02 - implement code
 * Inspiration from
 * <a href: Algorithms 4th edition> Chapter 4, section 1</a>
 *
 * */

public class SymbolGraph {

    private BinarySearchST<String, Integer> st;
    private String[] keys;
    private Graph g;

    /**
     * The constructor for the <code> SymbolGraph </code> class
     * that utilises both a symbol table, graph and string array.
     * @param stream contains the file to be read from.
     * @param sp contains the split command or where to split.
     * */
    public SymbolGraph(String stream, String sp) throws FileNotFoundException {
        st = new BinarySearchST<>();
        Scanner in = new Scanner(new File(stream));
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(sp);
            for (String s : a) {
                if (!st.contains(s))
                    st.put(s, st.size());
            }
        }
        keys = new String[st.size()];
        for (String name : st.keys())
            keys[st.get(name)] = name;

        g = new Graph(st.size());
        in = new Scanner(new File(stream));
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(sp);
            int v = st.get(a[0]);
            for (String s : a) {
                g.addEdge(v, st.get(s));
            }
        }
    }

    /**
     * Checks if it contains a string
     * @return true or false
     * */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * return the index of a string
     * @return index value.
     * */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * returns the name of a specific index
     * @return name of the index.
     * */
    public String name(int v) {
        return keys[v];
    }

    /**
     * returns the graph that is made for this symbol
     * graph.
     * @return the graph.
     * */
    public Graph G() {
        return g;
    }
}
