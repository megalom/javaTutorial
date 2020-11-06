package com.megalom.tutorial.streams;

import java.io.*;

public class FileStreams {
    public void fileByteOpenTest(){
        FileInputStream fis = null;
        int b = 0;
        String line = "";

        try {
            fis = new FileInputStream("/home/megalom/test.txt");
            while( (b = fis.read()) != -1 ){
                line += (char) b;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(line);
        System.out.println("Byte file open complete.");
    }
    public void fileTextOpenTest(){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        int b = 0;
        String line = "";

        try {
            fis = new FileInputStream("/home/megalom/test.txt");
            isr = new InputStreamReader(fis,"UTF-8");
            while( (b = isr.read()) != -1 ){
                line += (char) b;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(line);
        System.out.println("Text file open complete");
    }
    public void fileTextSaveTest(){
        String str = "Hello output stream!\nПривет выходной поток";
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream("/home/megalom/SaveTest.txt");
            osw = new OutputStreamWriter(fos,"UTF-8");
            int i=0;
            while(i<str.length()){
                osw.write(str.charAt(i));
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File output error.");
            e.printStackTrace();
        }
        finally {
            try {

                osw.close();
            } catch (IOException e) {
                System.out.println("osw close Error.");
                e.printStackTrace();
            }

            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("File close Error.");
                e.printStackTrace();
            }

        }
        System.out.println("File \"SaveTest.txt\" save complete.");
    }
    public void fileWriterTest(){
        FileWriter fw = null;
        String str = "Append String. Добавляем строку";
        try {
            fw = new FileWriter("/home/megalom/test.txt",true);
            fw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Save \"test.txt\" complete.");
    }
}
