package structs;

import java.util.HashSet;
import java.util.Iterator;

public class Vertex implements Comparable<Vertex>{
    private HashSet<Integer> neighbors;
    private int maxDegree;
    private int countNeighbors;
    private int id;

    Vertex(int id) {
        neighbors = new HashSet<>();
        this.id = id;
        countNeighbors = 0;
    }

    /**
     * Cost: O(1)
     * @param v
     */
    void addEdge(int v){
        neighbors.add(v);
        countNeighbors++;
        maxDegree = countNeighbors;
    }

    /**
     * Cost: O(1)
     * @param v
     */
    void deleteEdge(int v){
        neighbors.remove(v);
        countNeighbors--;
    }

    /**
     * Cost: O(Grade(thisVertex))
     */
    void deleteAllEdges(){
        neighbors.clear();
        countNeighbors = 0;
    }

    public Iterator<Integer> getNeighbors(){
        return neighbors.iterator();
    }

    /**
     *
     * @return Number of neighbors of the vertex
     */
    public int getCountNeighbors(){
        return countNeighbors;
    }
    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.maxDegree == o.maxDegree){
            return Integer.compare(this.id, o.id);
        }
        return Integer.compare(maxDegree, o.maxDegree);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vertex && id == ((Vertex) obj).getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
