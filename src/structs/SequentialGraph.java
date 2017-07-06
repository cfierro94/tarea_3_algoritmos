package structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Graph that allows to iterate over its edges
 */
public class SequentialGraph implements Graph{
    private HashMap<Integer, Vertex> graph;
    private int m, n;
    private int countDeletedEdges;

    /**
     * Cost: O(n = |V|)
     * @param n
     */
    public SequentialGraph(int n) {
        this.n = n;
        graph = new HashMap<>(n+1);
        for (int i = 1; i < n+1; i++) {
            graph.put(i, new Vertex(i));
        }
        countDeletedEdges = 0;
        m = 0;
    }

    /**
     * Cost: O(1)
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        graph.get(u).addEdge(v);
        graph.get(v).addEdge(u);
        m++;
    }

    /**
     * Searches for some edge in the graph
     * Cost = O(1)
     * @return Edge represented with a two vertices list
     */
    public Vertex[] getNextEdge() {
        ArrayList<Integer> toDelete = new ArrayList<>();
        for (HashMap.Entry<Integer, Vertex> pair : graph.entrySet()) {
            Vertex v = pair.getValue();
            if (v.getCountNeighbors() > 0)
                return new Vertex[]{v, graph.get(v.getNeighbors().next())};
            else
                toDelete.add(pair.getKey()); // If vertex is disconnected, append it for deletion
        }
        for (Integer key : toDelete){
            graph.remove(key);
        }
        return null;
    }

    /**
     * Cost: O(1)
     * @return True if there is at least one more edge in the graph
     */
    public boolean hasNextEdge() {
        return countDeletedEdges != m;
    }


    /**
     * Cost: O(Grade(u))
     *
     * @param vertex
     * @return
     */
    public void deleteVertexNEdges(Vertex vertex) {
        countDeletedEdges += vertex.getCountNeighbors();
        Iterator<Integer> iterator = vertex.getNeighbors();
        while (iterator.hasNext()){
            int next = iterator.next();
            graph.get(next).deleteEdge(vertex.getId());
            if (graph.get(next).getCountNeighbors() <= 0) // if node is disconnected, isn't needed anymore
                graph.remove(graph.get(next).getId());
        }
        vertex.deleteAllEdges();
        graph.remove(vertex.getId()); // node is disconnected so isn't needed anymore
    }
}
