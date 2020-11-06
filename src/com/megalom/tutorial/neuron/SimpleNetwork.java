package com.megalom.tutorial.neuron;

public class SimpleNetwork {
    NNetwork network;
    public SimpleNetwork(String filename, int inputSize, int outputSize){
        network = new NNetwork(inputSize,2,2,outputSize);
    }

    public void train(double[] inputData,double[] target,double learningRate,int epochs){
        for(int epoch=1;epoch<=epochs;epoch++){
            progressBar(epoch,epochs);
            long start=System.currentTimeMillis();
            network.train(inputData,target,learningRate);
            long end=System.currentTimeMillis();
            System.out.println("1epoch time:" +(end-start));
        }

    }

    public void test(double[] inputData){

    }

    public static void progressBar(int epoch,int epochs){
        float stage=((float)epoch/epochs)*100;
        if(stage-(int)stage==0 && stage%5==0){
            System.out.print((int)stage+" ");
        }
        if(epoch==epochs)
            System.out.println("done");
    }


}
