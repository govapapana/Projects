package com.AirportBaggage.main;

import java.util.List;

import com.AirportBaggage.pojo.DirectedEdge;


public interface AirportAlgorithm {
    public String findShortestPath(String entry,String dest, List<DirectedEdge> graphEdges);
}
