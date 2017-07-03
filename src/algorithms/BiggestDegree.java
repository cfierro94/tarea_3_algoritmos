package algorithms;

import structs.OrderedGraph;
import structs.Pair;
import structs.Vertex;

import java.util.ArrayList;

/**
 * Created by constanzafierro on 24-06-17.
 */
public class BiggestDegree {
    OrderedGraph graph;

    public BiggestDegree(ArrayList<Pair> edges) {
        graph = new OrderedGraph();
        for (Pair p: edges){
            graph.addEdge(p.u, p.v);
        }
    }

    public int getCVM(){
        int cDimension = 0;
        while (graph.hasNextVertex()){
            Vertex v = graph.getBiggestDegreeVertex();
            cDimension++;
            graph.deleteVertex(v);
        }
        return cDimension;
    }
}
