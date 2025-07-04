package org.example.graphTravelers;

// import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.example.adapter.GraphAdapter;

public class BfsGraphTraverser implements Traverser {
    private GraphAdapter<Integer, String> adapterForGraph;

    public BfsGraphTraverser(GraphAdapter<Integer, String> graphAdapter) {
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
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            result.add(vertex);

            // Get neighbors and sort them for deterministic output
            List<Integer> neighbors = new ArrayList<>(adapterForGraph.getNeighbors(vertex));
            neighbors.sort(Comparator.naturalOrder());

            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }
}