package com.citi.mongo.dao.impl;

import com.citi.mongo.dao.DepartmentDao;
import com.citi.mongo.model.Department;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Department object, String collectionName) {
        mongoTemplate.insert(object, collectionName);
    }

    @Override
    public Department findOne(Map<String, Object> params, String collectionName) {
        return null;
    }

    @Override
    public List<Department> findAll(Map<String, Object> params, String collectionName) {
        return null;
    }

    @Override
    public void update(Map<String, Object> params, String collectionName) {

    }

    @Override
    public void createCollection(String collectionName) {

    }

    @Override
    public void remove(Map<String, Object> params, String collectionName) {

    }
    @Override
    public void findByName2DBObject(){
        DBCollection depart = mongoTemplate.getCollection("department");
        DBCursor cur = depart.find();
        DBObject d = cur.next();
        System.out.println(d.get("name"));

        DBObject query1 = new BasicDBObject("name","PFR");
        DBCursor cur2 = depart.find(query1);
        DBObject d2 = cur2.next();
        List<DBObject>list = (List<DBObject>)d2.get("users");
        System.out.println(list.get(0).get("name"));
    }
}
