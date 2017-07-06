package algorithms;

import structs.OrderedGraph;
import structs.Pair;
import structs.Vertex;

import java.util.ArrayList;


public class Aprox2Improved implements CVMAlgorithm {
    private OrderedGraph graph;

    public Aprox2Improved(int n, int[] edges) {
        graph = new OrderedGraph(n);
        for (int i = 0; i < edges.length;){
            graph.addEdge(edges[i++], edges[i++]);
        }
        graph.makeTreeSet();
    }

    public int getCVM(){
        int cDimension = 0;
        while (graph.hasNextEdge()){
            Vertex v = graph.getBiggestDegreeVertex();
            Vertex u = graph.getMaxDegreeNeighbor(v);
            cDimension += 2;
            graph.deleteVertexNEdges(v);
            graph.deleteVertexNEdges(u);
        }
        return cDimension;
    }
}
