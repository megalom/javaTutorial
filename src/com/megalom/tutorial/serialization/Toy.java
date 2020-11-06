package com.megalom.tutorial.serialization;

import java.io.Serializable;

public class Toy implements Serializable {
    private String name;

    public Toy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
