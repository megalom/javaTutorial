package com.megalom.tutorial.serialization;

import java.io.*;

public class Serializator {

    public void serializationTest(){
        //write
        Cat cat = new Cat("Vasya","Klubok");
        Serializator serializator = new Serializator();
        System.out.println("Serialization complete: "+serializator.serialization(cat));

        //read

        Serializator serializator2 = new Serializator();
        try {
            Cat cat2 = serializator2.deserialization();
            System.out.println("Cat name = "+cat.getName()+", cat toy name = "+cat.getToyName());
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

    }

    public boolean serialization(Cat cat){
        boolean result = false;

        File file = new File("/home/megalom/cat.dat");
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            if(fos != null) {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(cat);
                result = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null)
                    oos.close();
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return result;
    }
    public Cat deserialization() throws InvalidObjectException {
        File file = new File("/home/megalom/cat.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Cat cat = null;

        try {
            fis = new FileInputStream(file);
            if(fis != null){
                ois = new ObjectInputStream(fis);
                return (Cat) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new InvalidObjectException("Object load fail");
    }
}
