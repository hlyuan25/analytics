package com.citi.mongo.dao;

import com.citi.mongo.model.User;
import com.citi.mongo.model.UserCount;

import java.util.List;

public interface UserDao extends MongoBase<User>{

    public List<UserCount> Select();
}
