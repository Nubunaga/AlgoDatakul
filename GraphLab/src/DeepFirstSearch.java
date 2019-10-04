import java.io.IOException;

/**
 * Compilation: javac DeepFirstSearch.java
 * Execution: java DeepFirstSearch.java TheDatabase.txt " " X Y
 * Dependencies: Stack.java, SymbolGraph.java
 * Input: TheDatabase.txt
 * #################################################################################
 * Output Test:
 * ---------------------------------------------------------------------------------
 * AL to AL:  AL
 * AL to FL:  AL-TN-VA-NC-SC-GA-FL
 * AL to GA:  AL-TN-VA-NC-SC-GA
 * AL to MS:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-AR-MS
 * AL to TN:  AL-TN
 * AL to AR:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-AR
 * AL to LA:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-AR-MS-LA
 * AL to MO:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO
 * AL to OK:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK
 * AL to TX:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX
 * AL to AZ:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ
 * AL to CA:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA
 * AL to NM:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM
 * AL to NV:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV
 * AL to UT:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT
 * AL to OR:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR
 * AL to CO:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-NE-KS-CO
 * AL to KS:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-NE-KS
 * AL to NE:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-NE
 * AL to WY:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY
 * AL to CT:  AL-TN-VA-WV-PA-NY-VT-NH-MA-RI-CT
 * AL to MA:  AL-TN-VA-WV-PA-NY-VT-NH-MA
 * AL to NY:  AL-TN-VA-WV-PA-NY
 * AL to RI:  AL-TN-VA-WV-PA-NY-VT-NH-MA-RI
 * AL to DC:  AL-TN-VA-WV-PA-NY-NJ-DE-MD-DC
 * AL to MD:  AL-TN-VA-WV-PA-NY-NJ-DE-MD
 * AL to VA:  AL-TN-VA
 * AL to DE:  AL-TN-VA-WV-PA-NY-NJ-DE
 * AL to NJ:  AL-TN-VA-WV-PA-NY-NJ
 * AL to PA:  AL-TN-VA-WV-PA
 * AL to NC:  AL-TN-VA-NC
 * AL to SC:  AL-TN-VA-NC-SC
 * AL to IA:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-NE-IA
 * AL to IL:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-NE-IA-IL
 * AL to MN:  AL-TN-VA-WV-PA-OH-MI-WI-MN
 * AL to SD:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD
 * AL to WI:  AL-TN-VA-WV-PA-OH-MI-WI
 * AL to ID:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-WA-ID
 * AL to MT:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-WA-ID-MT
 * AL to WA:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-WA
 * AL to IN:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-NE-IA-IL-KY-IN
 * AL to KY:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-CA-AZ-NM-TX-OK-MO-NE-IA-IL-KY
 * AL to MI:  AL-TN-VA-WV-PA-OH-MI
 * AL to OH:  AL-TN-VA-WV-PA-OH
 * AL to WV:  AL-TN-VA-WV
 * AL to NH:  AL-TN-VA-WV-PA-NY-VT-NH
 * AL to VT:  AL-TN-VA-WV-PA-NY-VT
 * AL to ME:  AL-TN-VA-WV-PA-NY-VT-NH-ME
 * AL to ND:  AL-TN-VA-WV-PA-OH-MI-WI-MN-SD-WY-UT-NV-OR-WA-ID-MT-ND
 *
 * From: AL To: FL
 * AL to FL:  AL-TN-VA-NC-SC-GA-FL
 * Runtime for program is: 1.219838 ms
 * ---------------------------------------------------------------------------------
 **/

/**
 * #################################################################################
 * Question:
 * Write a program based on DFS which can answer questions of the type: "Find the a
 * path from X to Y" Which should result in a list of vertices traversed from X to
 * Y if there is a path.
 * #################################################################################
 *
 * <p>
 * This program implements the deep first search algorithm. This algorithm provides a
 * path through the graph by using a boolean array <em> marked </em> that each time
 * it finds a way between graphs sets the array index as true. Method
 * <code> pathTo() </code> checks this arrays and pushes the given index to the stack,
 * see <code> {@link Stack} </code> in a LIFO ADT. This will build a linked list of all
 * the "paths" connected to the source. This is utilised to print out the list to get
 * the desired result provided above.
 *
 * @author Netanel Avraham Eklind
 * @version 1: 2019-10/01 - Implement a DFS graph basic operations.
 */

public class DeepFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private Graph g;

    /**
     * Constructor for the Depth first algorithm class
     *
     * @param g contains the graph to look through
     * @param s the source of the graph.
     */
    public DeepFirstSearch(Graph g, int s) {
        this.g = g;
        marked = new boolean[g.v()];
        edgeTo = new int[g.v()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    /**
     * Checks if there is a path to the next node
     *
     * @param v contains the source of the node
     */
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
        DeepFirstSearch deepFirstSearch = new DeepFirstSearch(symbolGraph.G(), symbolGraph.index(source));
        Double endTime = ((System.nanoTime() - starting) / (1 * Math.pow(10, 6)));

        //Test printout
        for (int v = 0; v < symbolGraph.G().v(); v++) {
            if (deepFirstSearch.hasPathTo(v)) {
                System.out.printf("%s to %s:  ", source , symbolGraph.name(v));

                for (int x : deepFirstSearch.pathTo(v)) {
                    if (x == symbolGraph.index(source)) System.out.print(symbolGraph.name(x));
                    else System.out.print("-" + symbolGraph.name(x));
                }
                System.out.println();
            }
        }

        //Main assignment
        System.out.println();
        System.out.println("From: " + source + " To: " + end);
        if(deepFirstSearch.hasPathTo(symbolGraph.index(end))){
            System.out.printf("%s to %s:  ", source , end);
        }
        for (int x : deepFirstSearch.pathTo(symbolGraph.index(end))){
            if (x == symbolGraph.index(source)) System.out.print(symbolGraph.name(x));
            else System.out.print("-" + symbolGraph.name(x));
        }



        System.out.print("\nRuntime for program is: " + endTime + " ms");
    }
}
