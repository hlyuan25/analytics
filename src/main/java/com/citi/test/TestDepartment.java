package com.citi.test;

import com.citi.mongo.dao.DepartmentDao;
import com.citi.mongo.model.Department;
import com.citi.mongo.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDepartment {
    private static DepartmentDao departmentDaoImpl;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;

    @BeforeTest
    public static void initSpring() {
        try {
            app = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml","classpath:applicationContext-mongo.xml"});
            departmentDaoImpl = (DepartmentDao) app.getBean("departmentDaoImpl");
            collectionName ="department";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAdd()
    {
        User u1 = new User("1","hailong",25,"123");
        User u2 = new User("2","xiao",25,"123");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        Department department = new Department("CSL",users);
        departmentDaoImpl.insert(department,collectionName);


        User u3 = new User("1","xinyue",25,"123");
        User u4 = new User("2","fangxin",25,"123");
        List<User> users2 = new ArrayList<>();
        users2.add(u3);
        users2.add(u4);
        Department department2 = new Department("PFR",users2);
        departmentDaoImpl.insert(department2,collectionName);

//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("maxAge", 50);
//        List<User> list=userDaoImpl.findAll(params,collectionName);
//        System.out.println("user.count()=="+list.size());
    }
    @Test
    public void testSelect(){
        departmentDaoImpl.findByName2DBObject();
    }
}
