package com.citi.analytics.service;

import java.util.List;

import com.citi.analytics.entity.User;  

  
public interface UserService {  
    User load(Integer id);  
    User get(Integer id);  
    List<User> findAll();  
    void persist(User entity);  
    Integer save(User entity);  
    void saveOrUpdate(User entity);  
    void delete(Integer id);  
    void flush();  
}  
