package algorithms;

import structs.Pair;
import structs.SequentialGraph;
import structs.Vertex;

import java.util.ArrayList;


public class Aprox2 implements CVMAlgorithm{
    SequentialGraph graph;

    public Aprox2(int n, int[] edges) {
        graph = new SequentialGraph(n);
        for (int i = 0; i < edges.length;){
            graph.addEdge(edges[i++],edges[i++]);
        }
    }

    public int getCVM(){
        int cDimension = 0;
        while (graph.hasNextEdge()){
            Vertex[] vertices = graph.getNextEdge();
            graph.deleteVertexNEdges(vertices[0]);
            graph.deleteVertexNEdges(vertices[1]);
            cDimension += 2;
        }
        return cDimension;
    }
}
