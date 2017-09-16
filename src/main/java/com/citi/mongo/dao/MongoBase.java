package com.citi.mongo.dao;

import java.util.List;
import java.util.Map;

public interface MongoBase <T>{
    //insert
    public void insert(T object,String collectionName);
    //find one
    public T findOne(Map<String,Object> params, String collectionName);
    //find all
    public List<T> findAll(Map<String,Object> params, String collectionName);
    //update
    public void update(Map<String,Object> params,String collectionName);
    //create
    public void createCollection(String collectionName);
    //delete
    public void remove(Map<String,Object> params,String collectionName);
}
