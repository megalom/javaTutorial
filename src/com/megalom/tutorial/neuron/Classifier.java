package com.megalom.tutorial.neuron;

import java.io.File;
import java.util.Arrays;

public class Classifier {
    private NNetwork network;
    private String filename;
    private String[] className;
    private TrainSet trainSet;
    
    public Classifier(String filename, int imageSize, int numberOfClasses){
        network = new NNetwork(imageSize*imageSize*3,3000,1000,numberOfClasses);
        File file = new File(filename);
        if(file.exists()==true){
            network.loadNetwork(filename);
        }
        
        className = new String[numberOfClasses];
        for(int i=0;i<numberOfClasses;i++){
            className[i]="Class No:"+i;
        }
    }
    
    public void train(String trainDatasetFile){
        double learningRate = 0.3;
        trainSet = new TrainSet();
        trainSet.dataBatchLoad(trainDatasetFile);
        System.out.println("Training dataset out of "+trainSet.inputData.length+" files.");
        int epochs=100;

        for(int epoch=0;epoch<epochs;epoch++) {
            if(epoch%100==0) {
                System.out.println((int) ((float) epoch / epochs * 100));
            }
            for (int i = 0; i < trainSet.inputData.length; i++) {
                network.train(trainSet.inputData[i], trainSet.outputData[i], learningRate);
            }
        }
    }

    public void test(String testDataFile){
        trainSet = new TrainSet();
        trainSet.dataBatchLoad(testDataFile);
        className= trainSet.outputName;
        System.out.println("Running test data out of "+trainSet.inputData.length+" files");
        System.out.println(Arrays.toString(className));
        for(int i=0;i<trainSet.inputData.length;i++){
            System.out.println(Arrays.toString(network.calculate(trainSet.inputData[i])));
            //TrainSet.saveDataToRGBFile(trainSet.inputData[i],"/home/megalom/ai/out/test"+i+".png");
        }
    }
    
    public String classify(String imageName){
        int out=0;

        return className[out];
    }
    

}
