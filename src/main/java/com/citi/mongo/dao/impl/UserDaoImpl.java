package com.citi.mongo.dao.impl;

import com.citi.mongo.dao.UserDao;
import com.citi.mongo.model.User;
import com.citi.mongo.model.UserCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(User object,String collectionName) {

        mongoTemplate.insert(object, collectionName);
    }

    @Override
    public User findOne(Map<String,Object> params,String collectionName) {
        return mongoTemplate.findOne(
                new Query(Criteria.where("id").is(params.get("id"))),
                User.class,
                collectionName
        );
    }

    @Override
    public List<User> findAll(Map<String,Object> params,String collectionName) {
        List<User> result = mongoTemplate.find(
                new Query(Criteria.where("age").lt(params.get("maxAge"))),
                User.class,
                collectionName);
        return result;
    }

    @Override
    public void update(Map<String,Object> params,String collectionName) {
        mongoTemplate.upsert(
                new Query(Criteria.where("id").is(params.get("id"))),
                new Update().set("name", params.get("name")),
                User.class,
                collectionName
        );
    }

    @Override
    public void createCollection(String name) {

        mongoTemplate.createCollection(name);
    }


    @Override
    public void remove(Map<String, Object> params,String collectionName) {
        mongoTemplate.remove(
                new Query(Criteria.where("id").is(params.get("id"))),
                User.class,
                collectionName
        );
    }

    @Override
    public List<UserCount> Select() {
        Aggregation agg = newAggregation(
                project("name"),
               // unwind("tags"),
                group("name").count().as("n"),
                project("n").and("x").previousOperation()
               // sort(DESC, "n")
        );

        AggregationResults<UserCount> results = mongoTemplate.aggregate(agg, "users", UserCount.class);
        List<UserCount> userCount = results.getMappedResults();
        return userCount;
    }
}
