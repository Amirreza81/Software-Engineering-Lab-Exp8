package org.example.graphTravelers;

// import edu.uci.ics.jung.graph.SparseMultigraph;

import org.example.adapter.GraphAdapter;

// import edu.uci.ics.jung.graph.SparseMultigraph;

import java.util.*;

public class DfsGraphTraverser implements Traverser {
    // private final SparseMultigraph<Integer, String> graph;
    private GraphAdapter<Integer, String> adapterForGraph;

    public DfsGraphTraverser(GraphAdapter<Integer, String> graphAdapter) {
        this.adapterForGraph = graphAdapter;
    }

    @Override
    public void setAdapterForGraph(GraphAdapter<Integer, String> adapter) {
        this.adapterForGraph = adapter;
    }

    @Override
    public List<Integer> traverse(Integer startVertex) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Integer vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                result.add(vertex);

                // Get neighbors and sort them for deterministic output
                List<Integer> neighbors = new ArrayList<>(adapterForGraph.getNeighbors(vertex));
                // neighbors.sort(Integer::compareTo); // ترتیب صعودی همسایگان

                Collections.reverse(neighbors);

                for (Integer neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return result;
    }
}