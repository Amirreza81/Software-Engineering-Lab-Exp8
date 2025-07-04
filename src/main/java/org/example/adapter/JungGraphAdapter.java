package org.example.adapter;

import edu.uci.ics.jung.graph.SparseMultigraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JungGraphAdapter<V, E> implements GraphAdapter<V, E> {
    private final SparseMultigraph<V, E> internalGraph;

    public JungGraphAdapter() {
        internalGraph = createGraph();
    }

    @Override
    public void addVertex(V vertex) {
        if (vertex == null) return;
        if (!internalGraph.containsVertex(vertex)) {
            doAddVertex(vertex);
        }
    }

    @Override
    public void addEdge(E edge, V v1, V v2) {
        if (edge == null || v1 == null || v2 == null) return;
        if (canAddEdge(edge, v1, v2)) {
            doAddEdge(edge, v1, v2);
        }
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        if (vertex == null || !internalGraph.containsVertex(vertex)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(internalGraph.getNeighbors(vertex));
    }

    @Override
    public Collection<V> getVertices() {
        return new ArrayList<>(internalGraph.getVertices());
    }

    private SparseMultigraph<V, E> createGraph() {
        return new SparseMultigraph<>();
    }

    private void doAddVertex(V vertex) {
        internalGraph.addVertex(vertex);
    }

    private void doAddEdge(E edge, V v1, V v2) {
        internalGraph.addEdge(edge, v1, v2);
    }

    private boolean canAddEdge(E edge, V v1, V v2) {
        return internalGraph.containsVertex(v1) && internalGraph.containsVertex(v2);
    }

    SparseMultigraph<V, E> getInternalGraph() {
        return internalGraph;
    }
}
