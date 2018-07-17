package com.AirportBaggage.pojo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class GraphMap {
    
    private final Map<String, Vertex> graphMap;
    public GraphMap(List<DirectedEdge> directedEdges) {

        graphMap = new HashMap<String, Vertex>(directedEdges.size());

       
        for (DirectedEdge e : directedEdges) {
            if (!graphMap.containsKey(e.getSource().getName())) graphMap.put(e.getSource().getName(), new Vertex(e.getSource().getName()));
            if (!graphMap.containsKey(e.getDestination().getName())) graphMap.put(e.getDestination().getName(), new Vertex(e.getDestination().getName()));
        }

        for (DirectedEdge e : directedEdges) {
            graphMap.get(e.getSource().getName()).getNeighbours().put(graphMap.get(e.getDestination().getName()), e.getTime());
        }
    }

    public void dijkstra(String startName) {
        if (!graphMap.containsKey(startName)) {
            throw new GraphMapException("This GraphMap does not contain the starting Vertex named:"+startName);
        }
        final Vertex source = graphMap.get(startName);
        NavigableSet<Vertex> queue = new TreeSet<Vertex>();

        for (Vertex v : graphMap.values()) {
            v.setPrevVertext( v == source ? source : null);
            v.setTime(v == source ? 0 : Integer.MAX_VALUE);
            queue.add(v);
        }

        dijkstra(queue);
    }

    
    public List<Vertex> getShortestPath(String endName){
        if (!graphMap.containsKey(endName)) {
            throw new GraphMapException("Graph doesn't contain end vertex : "+endName);
        }

        return graphMap.get(endName).getShortestPathTo();
    }

    private void dijkstra(final NavigableSet<Vertex> que) {
        Vertex source, neighbour;
        while (!que.isEmpty()) {

            source = que.pollFirst(); 
            if (source.getTime() == Integer.MAX_VALUE) break;

            for (Map.Entry<Vertex, Integer> a : source.getNeighbours().entrySet()) {
                neighbour = a.getKey();

                final int alternateTime = source.getTime() + a.getValue();
                if (alternateTime < neighbour.getTime()) { 
                    que.remove(neighbour);
                    neighbour.setTime(alternateTime);
                    neighbour.setPrevVertext(source);
                    que.add(neighbour);
                }
            }
        }
    }
}
