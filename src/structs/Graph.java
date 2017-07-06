package structs;


public interface Graph {
    void deleteVertexNEdges(Vertex vertex);
    void addEdge(int u, int v);
    boolean hasNextEdge();
}
