package tests;

import algorithms.Aprox2;
import algorithms.Aprox2Improved;
import algorithms.BiggestDegree;
import algorithms.CVMAlgorithm;
import structs.Graph;
import structs.OrderedGraph;
import structs.Pair;
import structs.SequentialGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class ComparationTest {
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        firstTest();
    }

    /**
     * Adds edges between avery pair of vertex with same probability.
     *
     * @param n |V| of graph.
     * @param prob Double between 0 and 1 representing probability of edge between
     */
    static ArrayList<Pair> createEdgesByProbability(int n, double prob){
        ArrayList<Pair> edges = new ArrayList<>();
        // graph is instantiated and have nodes labeled from 1 to n
        for (int i = 1; i <= n - 1; i++){
            for (int j = i + 1; j <= n; j++){
                // double-for represents: n over 2 = n*(n-1)/2 = Sum_{i=0}^{n-1}(i) different combinations of 2 vertex.
                if (Math.random() <= prob)
                    edges.add(new Pair(i,j));
            }
        }
        return edges;
    }


    static void firstTest() throws FileNotFoundException, CloneNotSupportedException {
        File statisticsFile = new File("./results/aleatory_graphs_results.txt");
        PrintWriter pw = new PrintWriter(statisticsFile);


        double[] probabilities = {0.1, 0.15, 0.2, 0.25, 0.3};

        for (int i = 5; i <= 20; i++){
            long iTimer0 = System.currentTimeMillis();
            int vertexNumber = (int)Math.pow(2, i);
            pw.println("vertex_number: " + vertexNumber);
            System.out.println("vertex_number: " + vertexNumber);
            for (double prob : probabilities){
                long probTimer0 = System.currentTimeMillis();
                pw.println("edge_prob: " + prob);
                System.out.println("edge_prob: " + prob);

                // Construction: Takes O(|V|log(|V|)) time.
                long t0 = System.currentTimeMillis();
                ArrayList<Pair> edges = createEdgesByProbability(vertexNumber, prob);
                long t1 = System.currentTimeMillis();
                System.out.println("Time creating edges by prob " + (t1-t0) + " {ms] ");

                t0 = System.currentTimeMillis();
                CVMAlgorithm tester = new BiggestDegree(vertexNumber, edges);
                System.out.println("Time building BiggestDegree (ordered graph) " + (System.currentTimeMillis()-t0) + " [ms] ");

                // Execution: Takes O(|V|*maxDegree) each one.
                long execTimer0 = System.currentTimeMillis();
                int res1 = tester.getCVM();
                long execTimer1 = System.currentTimeMillis();
                pw.print("biggest_deg: " + res1 + " in " + (execTimer1 - execTimer0) + " [ms]; ");
                System.out.println("biggest_deg: " + res1 + " in " + (execTimer1 - execTimer0) + " [ms] ");

                t0 = System.currentTimeMillis();
                tester = new Aprox2(vertexNumber, edges);
                System.gc();
                System.out.println("Time building Aprox2 (sequential graph) " + (System.currentTimeMillis()-t0) + " [ms] ");
                execTimer0 = System.currentTimeMillis();
                int res2 = tester.getCVM();
                execTimer1 = System.currentTimeMillis();
                pw.print("2_aprox: " + res2 + " in " + (execTimer1 - execTimer0) + " [ms]; ");
                System.out.println("2_aprox: " + res2 + " in " + (execTimer1 - execTimer0) + " [ms] ");

                t0 = System.currentTimeMillis();
                tester = new Aprox2Improved(vertexNumber, edges);
                System.gc();
                System.out.println("Time building Aprox2Improved (ordered graph) " + (System.currentTimeMillis()-t0) + " [ms] ");
                execTimer0 = System.currentTimeMillis();
                int res3 = tester.getCVM();
                execTimer1 = System.currentTimeMillis();
                pw.println("better_2_aprox: " + res3 + " in " + (execTimer1 - execTimer0) + " [ms]; ");
                System.out.println("better_2_aprox: " + res3 + " in " + (execTimer1 - execTimer0) + " [ms] ");


                long probTimer1 = System.currentTimeMillis();
                System.out.println("  done prob: " + prob + " in " + (probTimer1 - probTimer0) + " [ms]");
                System.out.println();
                pw.println();
                pw.flush();
          }
            long iTimer1 = System.currentTimeMillis();
            System.out.println(" done |v|: " + vertexNumber + " in " + (iTimer1 - iTimer0) + " [ms] ");
            System.out.println();
            pw.println();
        }
        pw.close();
    }

}

