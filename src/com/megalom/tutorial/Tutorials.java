package com.megalom.tutorial;

import com.megalom.tutorial.collections.CMethods;
import com.megalom.tutorial.collections.ComparatorTut;
import com.megalom.tutorial.exceptions.ExceptionExample;
import com.megalom.tutorial.generic.GenericTut;
import com.megalom.tutorial.multithreading.multithreadingTut;
import com.megalom.tutorial.postgresql.HelloSQL;
import com.megalom.tutorial.serialization.Serializator;
import com.megalom.tutorial.streams.FileStreams;

import java.sql.SQLException;

public class Tutorials {
    FileStreams fileStreams = new FileStreams();
    Serializator serializator = new Serializator();
    GenericTut genericTut = new GenericTut();
    ExceptionExample exceptionExample = new ExceptionExample();
    CMethods collectionsMethods = new CMethods();

    public void fileStreamTutorial(){
        System.out.println("File streams example:");
        fileStreams.fileWriterTest();
        fileStreams.fileByteOpenTest();
        fileStreams.fileTextSaveTest();
        fileStreams.fileTextOpenTest();
    }

    public void serializationTutorial(){
        System.out.println("Serialization example:");
        serializator.serializationTest();
    }

    public void genericTutorial(){
        //genericTut
    }

    public void exceptionTutorial(){
        System.out.println("Exception example:");
        exceptionExample.exceptionExampleMain();
    }

    public void collectionsTutorial(){
        System.out.println("Collections example:");
        System.out.println("List tutorial:");
        collectionsMethods.listTut();
        System.out.println("Linked list tutorial:");
        //collectionsMethods.linkedListTut();
        System.out.println("HashMap tutorial");
        collectionsMethods.hashMapTutorial();
        collectionsMethods.hashMapTutorial2();
        collectionsMethods.setTest();
        collectionsMethods.setOperationsTest();
        collectionsMethods.hashCodeTest();
        ComparatorTut.start();

    }
    public void multithreadingTutorial(){
        multithreadingTut.VolatileTest();
    }

    public void postgresTutorial(){
        System.out.println("Postgresql example:");
        HelloSQL helloSQL = new HelloSQL();
        try {
            helloSQL.testSQL();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
