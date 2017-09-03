package com.citi.mongo.model;

public class UserCount {
    String name;
    int n;

    public String getName() {
        return name;
    }

    public int getN() {
        return n;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "UserCount{" +
                "name='" + name + '\'' +
                ", n=" + n +
                '}';
    }
}
