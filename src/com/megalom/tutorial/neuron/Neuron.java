package com.megalom.tutorial.neuron;

public class Neuron {
    private double weight = 0.5;
    private double learningRate = 0.00001;
    public double lastError;

    public double processInputData(double input){
        return input*weight;
    }

    public double restoreInputData(double output){
        return output*weight;
    }

    public void Train(double input, double expectedResult){
        double actualResult = input*weight;
        lastError = expectedResult - actualResult;
        double correction = lastError / actualResult;
        weight += correction*learningRate;
    }
}
