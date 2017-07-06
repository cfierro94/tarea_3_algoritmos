package algorithms;

import structs.OrderedGraph;
import structs.Pair;
import structs.Vertex;

import java.util.ArrayList;


public class BiggestDegree implements CVMAlgorithm{
    private OrderedGraph graph;

    public BiggestDegree(int n, int[] edges) {
        graph = new OrderedGraph(n);
        for (int i = 0; i < edges.length;){
            graph.addEdge(edges[i++],edges[i++]);
        }
        graph.makeTreeSet(); //full ordered-degree container struct.
    }

    public int getCVM(){
        int cDimension = 0;
        while (graph.hasNextEdge()){
            Vertex v = graph.getBiggestDegreeVertex();
            cDimension++;
            graph.deleteVertexNEdges(v);
        }
        return cDimension;
    }
}
