package com.AirportBaggage.main;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.AirportBaggage.pojo.GraphMap;
import com.AirportBaggage.pojo.DirectedEdge;
import com.AirportBaggage.pojo.Vertex;

public class AlgorithmImpl implements AirportAlgorithm {

    private final static String SINGLE_WHITE_SPACE=" ";

    Map<String, GraphMap> visitedMap=new ConcurrentHashMap<String, GraphMap>(); 

    public String findShortestPath(String entryGate, String destGate, List<DirectedEdge> edges) {
        GraphMap graphMap;
        if(visitedMap.containsKey(entryGate)){
            graphMap = visitedMap.get(entryGate);
        }else {
            graphMap = new GraphMap(edges);
            graphMap.dijkstra(entryGate);
            visitedMap.put(entryGate,graphMap);
        }

        List<Vertex> shortestPath= graphMap.getShortestPath(destGate);
        return generatePathLine(shortestPath);
    }
    private String generatePathLine(List<Vertex> path){
        StringBuffer line = new StringBuffer();

        for(Vertex vertex:path){
            line.append(vertex.getName()).append(SINGLE_WHITE_SPACE);
        }
        line.append(": ").append(path.get(path.size()-1).getTime());
        return line.toString();
    }


	
    

}
