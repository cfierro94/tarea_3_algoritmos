package tests;


import structs.SequentialGraph;
import structs.Vertex;

public class SequentialGraphTest {
    public static void main(String[] args) throws Exception {
        edgeLessGraph();
        oneToNGraph();
    }

    private static void edgeLessGraph(){
        int n = 1000;
        SequentialGraph seqGraph = new SequentialGraph(n);
        asserto(!seqGraph.hasNextEdge());
        asserto(seqGraph.getNextEdge() == null);
    }

    private static void oneToNGraph(){
        int n = 1000;
        SequentialGraph seqGraph = new SequentialGraph(n);
        for (int i = 2; i < n+1; i++){
            seqGraph.addEdge(1, i);
        } // 1 nodo conectado al resto

        asserto(seqGraph.hasNextEdge());
        Vertex[] pairOfVertex = seqGraph.getNextEdge();

        asserto( pairOfVertex[0].getCountNeighbors() > 0);
        asserto( pairOfVertex[1].getCountNeighbors() > 0);
        // one of those should be the fully connected vertex

        int maxGrade = Math.max(pairOfVertex[0].getCountNeighbors(), pairOfVertex[1].getCountNeighbors());
        int minGrade = Math.min(pairOfVertex[0].getCountNeighbors(), pairOfVertex[1].getCountNeighbors());
        asserto(minGrade == 1);
        asserto(maxGrade == n-1);

        seqGraph.deleteVertexNEdges(pairOfVertex[0]);
        seqGraph.deleteVertexNEdges(pairOfVertex[1]);
        // this should delete all edges
        asserto(!seqGraph.hasNextEdge());
        asserto(seqGraph.getNextEdge() == null);
    }


    private static void asserto(Boolean result) {
        if (result) {
            System.out.println("    pass.");
            return;
        }
        throw new AssertionError();
    }




}
