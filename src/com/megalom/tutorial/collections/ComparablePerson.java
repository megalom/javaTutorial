package com.megalom.tutorial.collections;

import java.util.Objects;

public class ComparablePerson implements Comparable<ComparablePerson> {
    private int id;
    private String Name;

    public ComparablePerson(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparablePerson that = (ComparablePerson) o;
        return id == that.id &&
                Name.equals(that.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name);
    }

    @Override
    public int compareTo(ComparablePerson o) {
        return this.id>o.getId()?1:this.id<o.getId()?-1:0;
    }

    @Override
    public String toString(){
        return "Person: ["+this.getId()+"], name: "+this.getName();
    }
}
