package com.citi.test;

import com.citi.mongo.dao.UserDao;
import com.citi.mongo.model.User;
import com.citi.mongo.model.UserCount;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUser {
    private static UserDao userDaoImpl;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;

    @BeforeTest
    public static void initSpring() {
        try {
            app = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml","classpath:applicationContext-mongo.xml"});
            userDaoImpl = (UserDao) app.getBean("userDaoImpl");
            collectionName ="users";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAdd()
    {

        //添加一百个user
        for(int i=0;i<100;i++){
            User user =new User();
            user.setId(""+i);
            user.setAge(i);
            user.setName("zcy"+i);
            user.setPassword("zcy"+i);
            userDaoImpl.insert(user,collectionName);
        }
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("maxAge", 50);
        List<User> list=userDaoImpl.findAll(params,collectionName);
        System.out.println("user.count()=="+list.size());
    }
    @Test
    public void testSelect(){

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id","19");
        User user = userDaoImpl.findOne(params,"users");
        System.out.println(user.getAge());
        System.out.println(user.getId());
        System.out.println(user.getName());
    }
    @Test
    public void testSelect2(){
        List<UserCount> userCounts = userDaoImpl.Select();
        System.out.println(userCounts.size());
    }
    @Test
    public void testGit(){

    }
}
