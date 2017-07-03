package structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Graph that allows to iterate over its edges
 */
public class SequentialGraph {
    private HashMap<Integer, Vertex> graph;
    private int m, n;
    private int countDeletedEdges;

    public SequentialGraph(int n) {
        this.n = n;
        graph = new HashMap<>(n+1);
        for (int i = 1; i < n+1; i++) {
            graph.put(i, new Vertex(i));
        }
        countDeletedEdges = 0;
        m = 0;
    }

    public void addEdge(int u, int v) {
        graph.get(u).addEdge(v);
        graph.get(v).addEdge(u);
        m++;
    }

    /**
     * Searches for some edge in the graph
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
        Iterator<Integer> iterator = graph.get(u).getNeighbors();
        while (iterator.hasNext()){
            int next = iterator.next();
            graph.get(next).deleteEdge(u);
        }
        int total = graph.get(u).getCountNeighbors();
        graph.get(u).deleteAllEdges();
        graph.remove(u); // node is disconnected so isn't needed anymore
        return total;
    }
}
