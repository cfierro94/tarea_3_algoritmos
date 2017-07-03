package structs;

import java.util.HashSet;
import java.util.Iterator;

public class Vertex implements Comparable<Vertex>{
    private HashSet<Integer> neighbors;
    private int countNeighbors;
    private int id;

    Vertex(int id) {
        neighbors = new HashSet<>();
        this.id = id;
        countNeighbors = 0;
    }
    void addEdge(int v){
        neighbors.add(v);
        countNeighbors++;
    }
    void deleteEdge(int v){
        neighbors.remove(v);
        countNeighbors--;
    }

    void deleteAllEdges(){
        neighbors.clear();
        countNeighbors = 0;
    }

    Iterator<Integer> getNeighbors(){
        return neighbors.iterator();
    }

    /**
     *
     * @return Number of neighbors of the vertex
     */
    int getCountNeighbors(){
        return countNeighbors;
    }
    int getId() {
        return id;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(countNeighbors, o.getCountNeighbors());
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
