package com.megalom.tutorial.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionExample {
    private ABC abc;

    public void divisionError() {
        System.out.println(1 / 0);
    }

    public void overflowError() {
        int array[] = {1, 2, 3};
        array[5] = 3;
    }

    public void fileExample() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("");

    }

    public void fileExample2() throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void perimeterExample(String str) throws PerimeterException {
        Square square = new Square();

        try {
            double side = Double.parseDouble(str);
            square.setSide(side);
        } catch (NumberFormatException ne) {
            throw new PerimeterException("String is incorrect",ne);
        } catch (PerimeterException e) {
            System.err.println(e.getMessage());//e.printStackTrace();
        }
    }

    public void exceptionExampleMain() {
        try {
            perimeterExample("h");
        } catch (PerimeterException e) {
            e.printStackTrace();
        }
        //divisionError();
        //overflowError();
        /*
        try {
            abc.Show();
            divisionError();
        }
        catch (NullPointerException e){
            System.out.println("Reference to object abc is null");
            //e.printStackTrace();
        }
        catch (ArithmeticException e){
            System.out.println("Division by 0");
        }
        finally {
            System.out.println("Finally!");
        }
        try {
            fileExample();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

    }
}
