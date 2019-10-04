/**
 * Compilation: javac BreadthFirstPaths.java
 * Execution: java BreadthFirstPaths.java TheDatabase.txt " " FL AL
 * Dependencies: SymbolGraph.java, Stack.java, Queue.java, Graph.java
 * Input:TheDatabase
 * ##############################################################################
 * Output:
 * ------------------------------------------------------------------------------
 * AL to AL:  AL
 * AL to FL:  AL-FL
 * AL to GA:  AL-GA
 * AL to MS:  AL-MS
 * AL to TN:  AL-TN
 * AL to AR:  AL-TN-AR
 * AL to LA:  AL-MS-LA
 * AL to MO:  AL-TN-MO
 * AL to OK:  AL-TN-MO-OK
 * AL to TX:  AL-TN-AR-TX
 * AL to AZ:  AL-TN-MO-OK-NM-AZ
 * AL to CA:  AL-TN-MO-OK-NM-AZ-CA
 * AL to NM:  AL-TN-MO-OK-NM
 * AL to NV:  AL-TN-MO-OK-NM-AZ-NV
 * AL to UT:  AL-TN-MO-OK-CO-UT
 * AL to OR:  AL-TN-MO-NE-WY-ID-OR
 * AL to CO:  AL-TN-MO-OK-CO
 * AL to KS:  AL-TN-MO-KS
 * AL to NE:  AL-TN-MO-NE
 * AL to WY:  AL-TN-MO-NE-WY
 * AL to CT:  AL-TN-VA-WV-PA-NY-CT
 * AL to MA:  AL-TN-VA-WV-PA-NY-MA
 * AL to NY:  AL-TN-VA-WV-PA-NY
 * AL to RI:  AL-TN-VA-WV-PA-NY-MA-RI
 * AL to DC:  AL-TN-VA-DC
 * AL to MD:  AL-TN-VA-MD
 * AL to VA:  AL-TN-VA
 * AL to DE:  AL-TN-VA-MD-DE
 * AL to NJ:  AL-TN-VA-WV-PA-NJ
 * AL to PA:  AL-TN-VA-WV-PA
 * AL to NC:  AL-TN-NC
 * AL to SC:  AL-GA-SC
 * AL to IA:  AL-TN-MO-IA
 * AL to IL:  AL-TN-MO-IL
 * AL to MN:  AL-TN-MO-IA-MN
 * AL to SD:  AL-TN-MO-NE-SD
 * AL to WI:  AL-TN-MO-IL-WI
 * AL to ID:  AL-TN-MO-NE-WY-ID
 * AL to MT:  AL-TN-MO-NE-WY-MT
 * AL to WA:  AL-TN-MO-NE-WY-ID-WA
 * AL to IN:  AL-TN-KY-IN
 * AL to KY:  AL-TN-KY
 * AL to MI:  AL-TN-KY-OH-MI
 * AL to OH:  AL-TN-KY-OH
 * AL to WV:  AL-TN-VA-WV
 * AL to NH:  AL-TN-VA-WV-PA-NY-VT-NH
 * AL to VT:  AL-TN-VA-WV-PA-NY-VT
 * AL to ME:  AL-TN-VA-WV-PA-NY-VT-NH-ME
 * AL to ND:  AL-TN-MO-NE-SD-ND
 *
 * From: AL To: FL
 * AL to FL:  AL-FL
 * Runtime for program is: 1.208745 ms
 * ------------------------------------------------------------------------------
 * ##############################################################################
 */

import java.io.IOException;

/**
 * This class implements the <em> Breadth First Paths </em> algorithm witch utilises
 * both <code> {@link SymbolGraph} </code> and a <code> {@link Stack} </code> to
 * create the data structure and collect the path as a linked list of vertexes.
 * The method <code> bfs() </code> marks all the vertices that are connected to
 * source so the client can call the method <code> hasPathTo() </code> to determine
 * if it is connected to s and method call <code> pathTo() </code> gets a path between
 * source and destination with the property that no other such paths has fewer edges between
 * source and destination.
 *
 * @author Netanel Avraham Eklind
 * @version 1: 2019-10/09 - Implementing the BFS algorithm.
 * Inspiration:
 * <a href: Algorithm 4th edition> Chapter 4 section 1, algorithm 4.2 </a>
 * */
public class BreadthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    /**
     * Constructor for the bfs algorithm that sets up the parameters
     * for the algorithm to work.
     * @param g contains the graph to search.
     * @param s is the source.
     * */
    public BreadthFirstPaths(Graph g, int s) {
        marked = new boolean[g.v()];
        edgeTo = new int[g.v()];
        this.s = s;
        bfs(g, s);
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * Checks if there is a patch to destination
     * @param v  is the destination.
     * */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Saves the path so it can be printed out in a iterator
     *
     * @param v contains the source.
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    /**
     * The main function for the program, this calls and creates 2 objects that is used
     * to solve the assignment.
     * @param args takes the command line as args.
     *
     * !!*Disclaimer*!! The printout is taken from a code that can be found at
     * <a href: https://introcs.cs.princeton.edu/java/45graph/Graph.java.html> </a>
     * */
    public static void main(String[] args) throws IOException {

        SymbolGraph symbolGraph = new SymbolGraph(args[0], args[1]);
        String source = args[2];
        String end = args[3];
        double starting = System.nanoTime();
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(symbolGraph.G(), symbolGraph.index(source));
        Double endTime = ((System.nanoTime() - starting) / (1 * Math.pow(10, 6)));

        //Test printout
        for (int v = 0; v < symbolGraph.G().v(); v++) {
            if (breadthFirstPaths.hasPathTo(v)) {
                System.out.printf("%s to %s:  ", source, symbolGraph.name(v));

                for (int x : breadthFirstPaths.pathTo(v)) {
                    if (x == symbolGraph.index(source)) System.out.print(symbolGraph.name(x));
                    else System.out.print("-" + symbolGraph.name(x));
                }
                System.out.println();
            }
        }

        //Main assignment
        System.out.println();
        System.out.println("From: " + source + " To: " + end);
        if (breadthFirstPaths.hasPathTo(symbolGraph.index(end))) {
            System.out.printf("%s to %s:  ", source, end);
        }
        for (int x : breadthFirstPaths.pathTo(symbolGraph.index(end))) {
            if (x == symbolGraph.index(source)) System.out.print(symbolGraph.name(x));
            else System.out.print("-" + symbolGraph.name(x));
        }


        System.out.print("\nRuntime for program is: " + endTime + " ms");
    }
}
