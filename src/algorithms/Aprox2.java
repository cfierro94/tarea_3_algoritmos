package algorithms;

import structs.Pair;
import structs.SequentialGraph;
import structs.Vertex;

import java.util.ArrayList;

/**
 * Created by constanzafierro on 24-06-17.
 */
public class Aprox2 {
    SequentialGraph graph;

    public Aprox2(int n, ArrayList<Pair> edges) {
        SequentialGraph graph = new SequentialGraph(n);
        for (Pair p: edges){
            graph.addEdge(p.u, p.v);
        }
    }

    public int getCVM(){
        int cDimension = 0;
        while (graph.hasNextEdge()){
            Vertex[] vertices = graph.getNextEdge();
            graph.deleteEdges(vertices[0], vertices[1]);
            cDimension += 2;
        }
        return cDimension;
    }
}
