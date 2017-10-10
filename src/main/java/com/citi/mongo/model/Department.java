package com.citi.mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class Department {
    @Field("_id")
    private String id;
    private String name;
    private List<User> users;

    public Department(String name, List<User> users) {

        this.name = name;
        this.users = users;
    }
}
