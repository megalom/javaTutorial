package com.megalom.tutorial.neuron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainSet {
    public static final int IMAGE_WIDTH=32;
    public static final int IMAGE_HEIGHT=32;
    //public final int INPUT_SIZE;
    //public final int OUTPUT_SIZE;
    public double inputData[][];
    public double outputData[][];
    public String outputName[];

    public TrainSet(){

    }

    public TrainSet(int input_size, int output_size) {
        //INPUT_SIZE = input_size;
        //OUTPUT_SIZE = output_size;
    }

    //data pack load
    public void dataBatchLoad(String filename){

        String[] dataFile=loadStringsFromFile(filename);

        List<String>oNames = new ArrayList<String>();
        List<DataFileString> dataFileStrings= new ArrayList<>();

        // Get output names from data file to list of strings
        for(int i=0;i< dataFile.length;i++){
            if(dataFile[i].length()<2)continue;
            if(dataFile[i].contains("output="))
                oNames.add(dataFile[i].substring(7));
            else{
                dataFileStrings.add(new DataFileString(oNames.size()-1,dataFile[i]));
            }
        }

        // put output names to array
        outputName = new String[oNames.size()];
        for(int i =0;i<oNames.size();i++)
            outputName[i]=oNames.get(i);
        inputData = new double[dataFileStrings.size()][];
        outputData = new double[dataFileStrings.size()][outputName.length];
        // get data and put it to inputData and outputData
        for(int i=0;i<dataFileStrings.size();i++){
            for(int j=0;j<outputName.length;j++){
                outputData[i][j]=0;
            }
            outputData[i][dataFileStrings.get(i).outputIndex]=1;
            inputData[i]=loadDataFromRGBFile(dataFileStrings.get(i).filePath);
        }


    }

    // load data strings from file
    private static String[] loadStringsFromFile(String filename){
        // Open file and copy data to string
        String dataLine="";
        int b=0;
        try(FileInputStream fis= new FileInputStream(filename)) {
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            while((b=isr.read())!=-1){
                dataLine+=(char)b;
            }
            isr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataLine.split("\\n");
    }

    // Image save/load
    public static BufferedImage loadImage(String filename) throws IOException {
        return ImageIO.read(new File(filename));
    }

    public static void saveImage(BufferedImage bImage,String filename) throws IOException {
        String[] str = filename.split("\\.");
        String format;

        if(str.length<1){
            format="png";
            filename=filename.concat(".png");
        }
        else{
            format =str[str.length-1];
        }

        if(ImageIO.write(bImage,format,new File(filename))!=true)
            System.out.println("Error saving to file"+filename);
    }

    public static byte[] loadImageToBytes(String filename) throws IOException {
        File file = new File(filename);
        BufferedImage bImage = null;
        bImage = ImageIO.read(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        bos.close();
        return data;
    }

    //data save/load
    public static double[] loadDataFromRGBFile(String filename){
        double [] data=null;
        final int zSize=3;
        try {
            BufferedImage img = loadImage(filename);
            if(img == null)
                System.out.println("loading file "+filename+" failed.");

            int dataLength = img.getWidth()*img.getHeight()*3;
            data = new double[dataLength];
            for(int y=0;y<img.getHeight();y++){
                for(int x=0;x<img.getWidth();x++){
                    int col = img.getRGB(x,y);
                    int red = (col & 0x00ff0000) >>16;
                    int green = (col & 0x0000ff00) >>8;
                    int blue = (col & 0x000000ff);

                    double colorR=mapColorToDouble(red);
                    double colorG=mapColorToDouble(green);
                    double colorB=mapColorToDouble(blue);

                    data[y*img.getWidth()*3+x*3+0]=colorR;//r
                    data[y*img.getWidth()*3+x*3+1]=colorG;//g
                    data[y*img.getWidth()*3+x*3+2]=colorB;//b
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(data==null) {
            System.out.println("Error loading file "+filename+" data is null");
            data = new double[1];
        }
        return data;
    }

    public static void saveDataToRGBFile(double[] data, String filename){
        BufferedImage img = new BufferedImage(IMAGE_WIDTH,IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB);
        for(int y=0;y<IMAGE_HEIGHT;y++){
            for(int x = 0;x<IMAGE_WIDTH;x++){
                int color;
                int r=mapDoubleToColor(data[y*IMAGE_WIDTH*3+x*3+0]);
                int g=mapDoubleToColor(data[y*IMAGE_WIDTH*3+x*3+1]);
                int b=mapDoubleToColor(data[y*IMAGE_WIDTH*3+x*3+2]);
                color = r <<16 | g <<8 | b;
                img.setRGB(x,y,color);
            }
        }
        try {
            saveImage(img,filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //utilities
    public static double mapColorToDouble(int color){
        return (double)color/255;
    }

    public static int mapDoubleToColor(double color){
        return (int)(255*color);
    }

    public static void loadAndSaveImage(String filename){
        try {
            BufferedImage b = loadImage(filename);
            //int red = 0x00FA0000;
            //int green = 0x0000FF00;
            //int blue = 0x000000FF;
            //int color = blue;

            int col =0x00aabbcc;// b.getRGB(30,30);
            int red = (col & 0x00ff0000) >>16;
            int green = (col & 0x0000ff00) >>8;
            int blue = (col & 0x000000ff);
            col = red<<16|green<<8|blue;
            System.out.println(Integer.toHexString(col));
            /*
            float z = 0;
            float z2 = 1;
            float z3 = 0.5f;
            double[] data = new double[IMAGE_WIDTH*IMAGE_HEIGHT*3];
            for(int y=0;y<IMAGE_HEIGHT;y++)
                for(int x=0;x<IMAGE_WIDTH;x++){
                    data[y*IMAGE_WIDTH*3+x*3+0]=z;
                    data[y*IMAGE_WIDTH*3+x*3+1]=z2;
                    data[y*IMAGE_WIDTH*3+x*3+2]=z3;
                    z+=0.01;
                    z2-=0.01;
                }
            saveDataToFile(data,"/home/megalom/output.jpg");*/

            double[] data = loadDataFromRGBFile("/home/megalom/test.jpeg");
            saveDataToRGBFile(data,"/home/megalom/testoutput.png");

            //red
            //green
            //blue

            //saveImage(b2,"/home/megalom/Image.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class DataFileString{
        public int outputIndex;
        public String filePath;
        DataFileString(int o, String f){
            outputIndex=o;
            filePath = f;
        }
    }
}

