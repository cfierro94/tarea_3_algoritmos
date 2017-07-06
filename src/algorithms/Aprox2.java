package algorithms;

import structs.Pair;
import structs.SequentialGraph;
import structs.Vertex;

import java.util.ArrayList;


public class Aprox2 implements CVMAlgorithm{
    SequentialGraph graph;

    public Aprox2(int n, ArrayList<Pair> edges) {
        graph = new SequentialGraph(n);
        for (Pair p: edges){
            graph.addEdge(p.u, p.v);
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
