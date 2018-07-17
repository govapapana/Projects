package com.AirportBaggage.main;


public class AlgorithmFactory {

    public static AirportAlgorithm createAlgorithm(){
        return new AlgorithmImpl();
    }
}
