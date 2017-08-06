package com.citi.analytics.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.analytics.dao.UserDao;
import com.citi.analytics.entity.User;
import com.citi.analytics.service.UserService;
import com.citi.analytics.util.Encrypt;



@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao<User> userDao;

	public UserDao<User> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public User save(User user) {
		User t = new User();
		BeanUtils.copyProperties(user, t, new String[] { "pwd" });
		t.setId(UUID.randomUUID().toString());
		t.setCreatedatetime(new Date());
		t.setPwd(Encrypt.e(user.getPwd()));
		userDao.save(t);
		BeanUtils.copyProperties(t, user);
		return user;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", user.getName());
		params.put("pwd", Encrypt.e(user.getPwd()));
		User t = userDao.get(
				"from user t where t.name = :name and t.pwd = :pwd", params);
		if (t != null) {
			return user;
		}
		return null;
	}




	

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete Tuser t where t.id in (";
		for(int i = 0; i < nids.length; i++){
			if(i > 0){
				hql += ",";
			}
			hql += "'" + nids[i] +"'";
		}
		hql += ")";
		userDao.executeHql(hql);
	}

	@Override
	public User edit(User user) {
		User t = userDao.get(User.class, user.getId());
		BeanUtils.copyProperties(user, t, new String[]{"id","pwd"});
		return user;
	}
}
