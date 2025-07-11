package org.example.graphTravelers;

import java.util.List;

import org.example.adapter.GraphAdapter;

public interface Traverser {
    List<Integer> traverse(Integer startVertex);
    void setAdapterForGraph(GraphAdapter<Integer, String> adapter);
}