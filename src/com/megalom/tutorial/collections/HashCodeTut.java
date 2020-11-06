package com.megalom.tutorial.collections;

import java.util.*;

public class HashCodeTut {
    public void start(){
        Map<TestObject,String> map = new HashMap<>();
        Set<TestObject> set = new HashSet<>();

        TestObject testObject1 = new TestObject(1,"Mike");
        TestObject testObject2 = new TestObject(1,"Mike");

        map.put(testObject1,"123");
        map.put(testObject2, "456");

        set.add(testObject1);
        set.add(testObject2);

        System.out.println(map);
        System.out.println(set);


    }
}
class TestObject{
    private int id;
    private String name;

    public TestObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{"+
                "id = "+id+
                ", name = '"+name+'\''+
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }
    // {object} -> {int}
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    /*
    Контракт hashcode() equals()
        1) У двух проверяемых объектов вызываем hashcode();
        Если мы получили два разных числа значит объекты точно разные

        2) Если hash одинаковые: либо объекты одинаковые, либо коллизия,
        поэтому вызываем метод Equals, который выдает точный(но медленный ответ)

     */
}
