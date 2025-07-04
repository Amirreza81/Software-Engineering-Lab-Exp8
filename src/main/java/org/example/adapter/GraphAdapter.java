package org.example.adapter;

import java.util.Collection;

public interface GraphAdapter <V, E> {
    void addVertex(V vertex);
    void addEdge(E edge, V v1, V v2);


    Collection<V> getNeighbors(V vertex);
    Collection<V> getVertices();

}
