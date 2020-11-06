package com.megalom.tutorial.serialization;

import java.io.Serializable;

public class Cat implements Serializable {
    //private static final long serialVersionUID = 1L;
    private String name;
    private Toy toy;

    public Cat(String name,String toy_name){
        this.name = name;
        toy = new Toy(toy_name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToyName(){
        return toy.getName();
    }
    public void setToyName(String toy_name){
        toy.setName(toy_name);
    }
}
