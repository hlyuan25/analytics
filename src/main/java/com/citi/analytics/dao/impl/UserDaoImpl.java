package com.citi.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.citi.analytics.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl<Tuser> extends BaseDaoImpl<Tuser> implements UserDao<Tuser>{
	
}
