package com.citi.analytics.service;

import com.citi.analytics.entity.User;

public interface UserService {
	
	public User save(User user);
	
	public User login(User user);

	public void remove(String ids);
	
	public User edit(User user);
}
