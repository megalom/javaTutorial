package com.megalom.tutorial;

import com.megalom.tutorial.neuron.Classifier;
import com.megalom.tutorial.neuron.NNetwork;
import com.megalom.tutorial.neuron.SimpleNetwork;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Tutorials tutorial = new Tutorials();

        /**
        *   Streams tutorial
        */
        //tutorial.fileStreamTutorial();

        /**
         * Serialization tutorial
         */
        //tutorial.serializationTutorial();
        /**
         * Exceptions Tutorial
         */
        //tutorial.exceptionTutorial();
        /**
         * Collections tutorial
         */
        //tutorial.collectionsTutorial();
        /**
         * Multithreading tutorial
         */
        tutorial.multithreadingTutorial();
        /*
         */
        /**
         * PostgreSQL tutorial
         */
        //tutorial.postgresTutorial();
        /**
         * Neural Network tutorial
         */
       // TrainSet.loadAndSaveImage("/home/megalom/img.jpg");/
/*
        double[] input = {0.1,0.5,0.34,0.7};
        double[] input2 = {0.4,0.1,0.0,0.4};
        double[] target = {0,1,0,0};
        double[] target2 = {0,0,0,1};

        NNetwork network = new NNetwork(4,2,4);

        double[] result1 = network.calculate(input);
        System.out.println(Arrays.toString(network.calculate(input)));
        System.out.println(Arrays.toString(network.calculate(input2)));

        //network.saveNetwork("/home/megalom/nn1.nnn");
        for(int f=0;f<1000;f++){
            network.train(input,target,0.3);
            network.train(input2,target2,0.3);
        }

        System.out.println(Arrays.toString(network.calculate(input)));
        System.out.println(Arrays.toString(network.calculate(input2)));
        */

/*
        double[] result = network.calculate(input);
        System.out.println(Arrays.toString(result));

        network.loadNetwork("/home/megalom/nn1.nnn");

        double[] result2 = network.calculate(input);
        System.out.println(Arrays.toString(result2));



        Classifier classifier = new Classifier(
                "/home/megalom/ai/classifier.nn",32,2
        );
        classifier.train("/home/megalom/ai/training2.txt");
        classifier.test("/home/megalom/ai/training2.txt");//("/home/megalom/ai/test.txt");
        */


    }
}
