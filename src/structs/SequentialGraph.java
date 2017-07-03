package structs;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Graph that allows to iterate over its edges
 */
public class SequentialGraph {
    private HashSet<Vertex> graph;
    private int m, n;
    private int countDeletedEdges;

    public SequentialGraph(int n) {
        this.n = n;
        graph = new HashSet<>(n+1);
        for (int i = 1; i < n+1; i++) {
            graph.add(new Vertex(i));
        }
        countDeletedEdges = 0;
        m = 0;
    }

    public void addEdge(int u, int v) {
        graph..addEdge(v);
        graph[v].addEdge(u);
        m++;
    }

    /**
     * Searches for some edge in the graph
     * @return Edge represented with a two vertices list
     */
    public Vertex[] getNextEdge() {
        for (int i = 1; i < n+1; i++) {
            if (graph[i].getCountNeighbors() > 0)
                return new Vertex[]{graph[i], graph[graph[i].getNeighbors().next()]};
        }
        return null;
    }

    /**
     *
     * @return True if there is at least one more edge in the graph
     */
    public boolean hasNextEdge() {
        return countDeletedEdges != m;
    }

    public void deleteEdges(Vertex u, Vertex v) {
        countDeletedEdges += deleteEdgesVertex(u.getId());
        countDeletedEdges += deleteEdgesVertex(v.getId());
    }

    private int deleteEdgesVertex(int u) {
        Iterator<Integer> iterator = graph[u].getNeighbors();
        while (iterator.hasNext()){
            int next = iterator.next();
            graph[next].deleteEdge(u);
        }
        int total = graph[u].getCountNeighbors();
        graph[u].deleteAllEdges();
        return total;
    }
}
