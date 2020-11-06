package com.megalom.tutorial.neuron;

import java.io.*;
import java.util.Arrays;

//x[l][n] = bias + kSum(w[l][n][k]*output[l-1][k] where k is amount of previous neurons
//Activation function E(x) = 1/(1+pow(e,-x))
//O[l][n] = E(x[l][n])
public class NNetwork {

    private double[][] output; //[layer][neurons]
    private double[][][] weights;//[layer][layer neuron number][previous layer neuron number]
    private double[][] bias;
    private double[][] errors;
    private double[][] outputDerivative;

    public final int[] NETWORK_LAYER_SIZES;
    public final int INPUT_SIZE;
    public final int OUTPUT_SIZE;
    public final int NETWORK_SIZE;

    // Constructor
    public NNetwork(int... network_layer_sizes) {
        this.NETWORK_LAYER_SIZES = network_layer_sizes;
        this.INPUT_SIZE = NETWORK_LAYER_SIZES[0];
        this.NETWORK_SIZE = NETWORK_LAYER_SIZES.length;
        this.OUTPUT_SIZE = NETWORK_LAYER_SIZES[NETWORK_SIZE - 1];

        this.output = new double[NETWORK_SIZE][]; //that's our neurons
        this.weights = new double[NETWORK_SIZE][][];
        this.bias = new double[NETWORK_SIZE][];
        this.errors = new double[NETWORK_SIZE][]; //errors on each neuron
        this.outputDerivative = new double[NETWORK_SIZE][]; //calculated derivative on each neuron

        for (int i = 0; i < NETWORK_SIZE; i++) {
            this.output[i] = new double[NETWORK_LAYER_SIZES[i]];
            this.errors[i] = new double[NETWORK_LAYER_SIZES[i]];
            this.outputDerivative[i] = new double[NETWORK_LAYER_SIZES[i]];
            //this.bias[i] = new double[NETWORK_LAYER_SIZES[i]];
            this.bias[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], 0.3, 0.7);


            if (i > 0) {
                //weights[i] = new double[NETWORK_LAYER_SIZES[i]][NETWORK_LAYER_SIZES[i-1]];
                weights[i] = NetworkTools.createRandomArray(
                        NETWORK_LAYER_SIZES[i], NETWORK_LAYER_SIZES[i - 1], -0.3, 0.5
                );
            }
        }
    }

    public double[] calculate(double... input) {
        if (input.length != INPUT_SIZE) {
            System.out.println("Input parameter count is not equal to the input size of neural network input layer.");
            double[] result = {0};
            return result;
        }
        this.output[0] = input;
        for (int layer = 1; layer < NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {
                double sum = bias[layer][neuron];
                for (int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer - 1]; prevNeuron++) {
                    sum += output[layer - 1][prevNeuron] * weights[layer][neuron][prevNeuron];
                }
                output[layer][neuron] = sigmoid(sum);
                outputDerivative[layer][neuron] = output[layer][neuron] * (1 - output[layer][neuron]);
            }
        }
        return output[NETWORK_SIZE - 1];
    }

    // Training network

    public void train(TrainSet set, int loops, int batchSize){
        /*
        for(int i=0;i<loops;i++){
            TrainSet batch = set.extractBatch(batchSize);
            for (int b=0;b<batchSize;b++){
                this.train(batch.getInput(b),batch.getOutput, 0.3);
            }
        }*/
    }

    public void train(double[] input, double[] target, double learningRate) {
        if (input.length != INPUT_SIZE || target.length != OUTPUT_SIZE) {
            System.out.println("input or output size is not equivalent to input or output size of the network.");
            return;
        }
        calculate(input);
        backPropError(target);
        updateWeights(learningRate);

    }

    // Back prop & update

    public void backPropError(double[] target) {
        for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[NETWORK_SIZE - 1]; neuron++) {
            errors[NETWORK_SIZE - 1][neuron] = (output[NETWORK_SIZE - 1][neuron] - target[neuron]) *
                    outputDerivative[NETWORK_SIZE - 1][neuron];
        }
        for (int layer = NETWORK_SIZE - 2; layer > 0; layer--) {
            for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {
                double sum = 0;
                for (int nextNeuron = 0; nextNeuron < NETWORK_LAYER_SIZES[layer + 1]; nextNeuron++) {
                    sum += weights[layer + 1][nextNeuron][neuron] * errors[layer + 1][nextNeuron];
                }
                this.errors[layer][neuron] = sum * outputDerivative[layer][neuron];

            }
        }
    }

    public void updateWeights(double learningRate) {
        for (int layer = 1; layer < NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {
                double delta = -learningRate * errors[layer][neuron];
                bias[layer][neuron] += delta;

                for (int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer - 1]; prevNeuron++) {
                    weights[layer][neuron][prevNeuron] += delta * output[layer - 1][prevNeuron];
                }

            }
        }
    }

    public double[]getWeights(int layerNumber,int neuron){
        return weights[layerNumber][neuron];
    }
    // Network save/load

    public void saveNetwork(String filename){
        try(FileOutputStream fos = new FileOutputStream(filename)){
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(NETWORK_SIZE);
            System.out.println(NETWORK_SIZE);
            for(int i=0;i<NETWORK_SIZE;i++) {
                dos.writeInt(NETWORK_LAYER_SIZES[i]);
            }
            System.out.println(Arrays.toString(NETWORK_LAYER_SIZES));

            for(int layer = 1; layer<NETWORK_SIZE;layer++)
                for(int layerNeuronNumber=0;layerNeuronNumber<NETWORK_LAYER_SIZES[layer];layerNeuronNumber++)
                    for(int previousLayerNeuronNumber=0;
                        previousLayerNeuronNumber<NETWORK_LAYER_SIZES[layer-1];
                        previousLayerNeuronNumber++){
                        dos.writeDouble(weights[layer][layerNeuronNumber][previousLayerNeuronNumber]);
                    }
            for(int layer=1;layer<NETWORK_SIZE;layer++)
                for(int neuron=0;neuron<NETWORK_LAYER_SIZES[layer];neuron++){
                    dos.writeDouble(bias[layer][neuron]);
                }
            dos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNetwork(String filename){
        try(FileInputStream fis = new FileInputStream(filename)) {
            DataInputStream dis = new DataInputStream(fis);
            int networkSize = dis.readInt();
            System.out.println(networkSize);
            int[] networkLayerSize = new int[networkSize];

            for(int i=0;i<networkSize;i++)
                networkLayerSize[i]=dis.readInt();
            System.out.println(Arrays.toString(networkLayerSize));
            weights=new double[networkSize][][];

            for(int layer = 1; layer<networkSize;layer++) {
                weights[layer] = new double[networkLayerSize[layer]][networkLayerSize[layer-1]];
                for (int layerNeuronNumber = 0; layerNeuronNumber < networkLayerSize[layer]; layerNeuronNumber++) {

                    for (int previousLayerNeuronNumber = 0;
                         previousLayerNeuronNumber < networkLayerSize[layer - 1];
                         previousLayerNeuronNumber++) {
                        weights[layer][layerNeuronNumber][previousLayerNeuronNumber] = dis.readDouble();

                        //dos.writeDouble(weights[layer][layerNeuronNumber][previousLayerNeuronNumber]);
                    }
                }
            }
            for(int layer=1;layer<networkSize;layer++)
                for(int neuron=0;neuron<networkLayerSize[layer];neuron++){
                    bias[layer][neuron]=dis.readDouble();
                }

            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // utilities

    private double sigmoid(double x) {
        return 1d / (1 + Math.exp(-x));
    }

}
