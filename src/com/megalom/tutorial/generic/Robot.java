package com.megalom.tutorial.generic;

public class Robot <T> {
    private T head;
    private Body body;

    public <T1 extends Number,T2 extends T1> void foo(T1 a, T2 b){

        System.out.println( a.doubleValue() + b.doubleValue());
    }

    public Robot(T head, Body body) {
        this.head = head;
        this.body = body;
    }

    public T getHead() {
        return head;
    }

    public Body getBody() {
        return body;
    }

}
