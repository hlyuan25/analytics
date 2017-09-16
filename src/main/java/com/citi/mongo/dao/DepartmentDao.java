package com.citi.mongo.dao;

import com.citi.mongo.model.Department;

public interface DepartmentDao extends MongoBase <Department>{
    void findByName2DBObject();
}
