package structs;

import java.util.*;

/**
 * Created by constanzafierro on 24-06-17.
 */
public class OrderedGraph{
    private TreeSet<Vertex> graph;
    private HashMap<Integer, Vertex> map;

    public OrderedGraph() {
        graph = new TreeSet<>(Comparator.reverseOrder());
        map = new HashMap<>();
    }

    private void addVertex(int u){
        if(!map.containsKey(u)) {
            Vertex vertex = new Vertex(u);
            graph.add(vertex);
            map.put(u, vertex);
        }
    }

    public void addEdge(int u, int v) {
        addVertex(u);
        map.get(u).addEdge(v);
        addVertex(v);
        map.get(v).addEdge(u);
    }

    public Vertex getNextVertex() {
        return graph.first();
    }

    /**
     *
     * @return True if there is at least one more vertex in the graph
     */
    public boolean hasNextVertex(){
        return !graph.isEmpty();
    }

    public void deleteVertex(Vertex vertex) {
        Iterator<Integer> neighborsIterator = map.get(vertex.getId()).getNeighbors();
        graph.remove(vertex);
        map.remove(vertex.getId());
        while (neighborsIterator.hasNext()){
            int next = neighborsIterator.next();
            map.get(next).deleteEdge(vertex.getId());
        }
    }

    /**
     * Searches for the neighbor with the biggest degree
     * @param vertex    Vertex from which to find the neighbor
     * @return          The Vertex with the biggest degree
     */
    public Vertex getMaxDegreeNeighbor(Vertex vertex){
        int u = vertex.getId();
        Iterator<Integer> neighborsIterator = map.get(u).getNeighbors();
        int maxValue = Integer.MIN_VALUE;
        int maxVertex = -1;
        while (neighborsIterator.hasNext()){
            int v = neighborsIterator.next();
            if (map.get(v).getCountNeighbors() > maxValue){
                maxVertex = v;
                maxValue = map.get(v).getCountNeighbors();
            }
        }
        return map.get(maxVertex);
    }
}
