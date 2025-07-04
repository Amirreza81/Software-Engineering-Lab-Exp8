package org.example.adapter;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JGraphTGraphAdapter<V, E> implements GraphAdapter<V, E> {
    private final Graph<V, DefaultEdge> internalGraph;

    public JGraphTGraphAdapter() {
        this.internalGraph = initializeGraph();
    }

    @Override
    public void addVertex(V vertex) {
        if (vertex != null && !internalGraph.containsVertex(vertex)) {
            insertVertex(vertex);
        }
    }

    @Override
    public void addEdge(E edge, V v1, V v2) {
        if (v1 == null || v2 == null) return;
        if (!internalGraph.containsVertex(v1)) insertVertex(v1);
        if (!internalGraph.containsVertex(v2)) insertVertex(v2);
        createEdgeBetween(v1, v2);
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        if (vertex == null || !internalGraph.containsVertex(vertex)) {
            return Set.of();
        }

        Set<V> result = new HashSet<>();
        for (DefaultEdge e : internalGraph.edgesOf(vertex)) {
            V u = internalGraph.getEdgeSource(e);
            V v = internalGraph.getEdgeTarget(e);
            result.add(vertex.equals(u) ? v : u);
        }

        return result;
    }

    @Override
    public Collection<V> getVertices() {
        return new HashSet<>(internalGraph.vertexSet());
    }

    // Internal factory method
    private Graph<V, DefaultEdge> initializeGraph() {
        return new SimpleGraph<>(DefaultEdge.class);
    }

    private void insertVertex(V vertex) {
        internalGraph.addVertex(vertex);
    }

    private void createEdgeBetween(V v1, V v2) {
        internalGraph.addEdge(v1, v2);
    }

    Graph<V, DefaultEdge> getInternalGraph() {
        return internalGraph;
    }
}
