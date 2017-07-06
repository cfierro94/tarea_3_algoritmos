package tests;



import structs.OrderedGraph;
import structs.Vertex;



public class OrderedGraphTest {
    public static void main(String[] args) throws Exception {
        edgeLessGraph();
        oneToNGraph();
    }

    private static void edgeLessGraph(){
        int n = 1000;
        OrderedGraph ordGraph = new OrderedGraph(n);
        ordGraph.makeTreeSet();

        asserto(ordGraph.hasNextVertex());
        asserto(!ordGraph.hasNextEdge());
        asserto(ordGraph.getBiggestDegreeVertex().getCountNeighbors() == 0);
    }

    private static void oneToNGraph(){
        int n = 1000;
        OrderedGraph ordGraph = new OrderedGraph(n);
        for (int i = 2; i < n+1; i++){
            ordGraph.addEdge(1, i);
        } // 1 nodo conectado al resto
        ordGraph.makeTreeSet();

        asserto(ordGraph.hasNextVertex());
        asserto(ordGraph.hasNextEdge());
        Vertex v = ordGraph.getBiggestDegreeVertex();

        asserto( v.getCountNeighbors() > 0);
        // should be the fully connected vertex

        asserto(v.getCountNeighbors() == n-1);

        ordGraph.deleteVertexNEdges(v); // this should delete all edges
        asserto(!ordGraph.hasNextEdge());
        asserto(ordGraph.hasNextVertex());
        asserto(ordGraph.getBiggestDegreeVertex().getCountNeighbors() == 0);
    }


    private static void asserto(Boolean result) {
        if (result) {
            System.out.println("    pass.");
            return;
        }
        throw new AssertionError();
    }


}
