package algorithms;

import structs.OrderedGraph;
import structs.Pair;
import structs.Vertex;

import java.util.ArrayList;


public class Aprox2Improved implements CVMAlgorithm {
    private OrderedGraph graph;

    public Aprox2Improved(int n, ArrayList<Pair> edges) {
        graph = new OrderedGraph(n);
        for (Pair p: edges){
            graph.addEdge(p.u, p.v);
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
