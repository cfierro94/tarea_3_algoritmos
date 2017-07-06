package structs;

import java.util.*;


public class OrderedGraph implements Graph, Cloneable{
    private TreeSet<Vertex> graph;
    private HashMap<Integer, Vertex> map;

    public OrderedGraph(int n) {
        graph = new TreeSet<>(Comparator.reverseOrder());
        map = new HashMap<>();
        for (int i = 1; i < n+1; i++){
            this.addVertex(i);
        }
    }

    @Override
    public OrderedGraph clone() throws CloneNotSupportedException {
        return (OrderedGraph)super.clone();
    }


    /**
     * Cost: O(1)
     * @param u
     */
    private void addVertex(int u){
        if(!map.containsKey(u)) {
            Vertex vertex = new Vertex(u);
            map.put(u, vertex);
        }
    }

    /**
     * Create the ordered structure to respond for Max Degree queries.
     * This method should be called after adding all the edges.
     *
     * Cost: O(Sum_i=1^|V|[log(i)]) = O(|V|log(|V|))
     */
    public void makeTreeSet(){
        for (HashMap.Entry<Integer, Vertex> pair : map.entrySet()) {
            graph.add(pair.getValue());
        }
    }

    /**
     * Cost: O(log(graph.size))
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        map.get(u).addEdge(v);
        map.get(v).addEdge(u);
    }

    /**
     * Cost: O(1)
     * @return
     */
    public Vertex getBiggestDegreeVertex() {
        return graph.first();
    }

    /**
     *Cost: O(1)
     *
     * @return True if there is at least one more vertex in the graph
     */
    public boolean hasNextVertex(){
        return !graph.isEmpty();
    }

    /**
     *Cost: O(1)
     *
     * @return True if there is at least one more edge in the graph
     */
    public boolean hasNextEdge(){
        return graph.first().getCountNeighbors() > 0;
    }

    /**
     * Cost: O(log(graph.length)) + O(degree(vertex))
     * @param vertex
     */
    public void deleteVertexNEdges(Vertex vertex) {
        Iterator<Integer> neighborsIterator = vertex.getNeighbors();
        graph.remove(vertex);
        map.remove(vertex.getId());
        while (neighborsIterator.hasNext()){
            int next = neighborsIterator.next();
            map.get(next).deleteEdge(vertex.getId());
        }
    }

    /**
     * Searches for the neighbor with the biggest degree
     * Cost: O(Degree(vertex))
     *
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
