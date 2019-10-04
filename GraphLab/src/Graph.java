import java.util.Scanner;

public class Graph {

    private final int v;
    private int e;
    private Bag<Integer>[] adj;

    public Graph(int V){
        this.v = V;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int i = 0; i < V; i++){
            adj[i] = new Bag<>();
        }
    }
    public Graph (Scanner in){
        this(in.nextInt());
        this.e = in.nextInt();
        int v;
        int w;
        for(int i = 0; i < this.e; i++){
            v = in.nextInt();
            w = in.nextInt();
            addEdge(v,w);
        }
    }
    public int v(){
        return this.v;
    }
    public int e(){
        return this.e;
    }

    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
