package com.citi.analytics.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


import com.citi.analytics.entity.User;
import com.citi.analytics.service.UserService;
import com.citi.analytics.util.ResultData;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Results({  
    @Result(name = "success", location = "/user.jsp")  
})  

public class UserAction extends BaseAction  implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private User user = new User();
	List<User> userList;  

	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void reg() {
		ResultData result = new ResultData();
		try {
			userService.save(user);
			result.setSuccess(true);
			result.setMsg("register success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("register fail");
		}
		super.writeJson(result);
	}

	public void login() {
		ResultData result = new ResultData();
		User u = userService.login(user);
		if (u != null) {
			result.setSuccess(true);
			result.setMsg("login success");
		} else{
			result.setMsg("login fail");
		}
			
		super.writeJson(result);
	}
	
	
	public void add() {
		ResultData result = new ResultData();
		try {
			User u = userService.save(user);
			result.setSuccess(true);
			result.setMsg("add user success");
			result.setObj(u);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("add user fail");
		}
		super.writeJson(result);
	}
	public void remove(){
		userService.remove(user.getId());
		ResultData result = new ResultData();
		result.setSuccess(true);
		result.setMsg("remove success");
		super.writeJson(result);
	}
	public void edit(){
		ResultData result = new ResultData();
		User u = userService.edit(user);
		result.setSuccess(true);
		result.setMsg("edit success");
		result.setObj(u);
		
		super.writeJson(result);
	}
	
	@Action(value="user")    
    public String execute() throws Exception {
		 userList =  new ArrayList<User>();
    	 User user = new User();  
         user.setName("fengwusan");  
         user.setPwd("123456");  
         User user2 = new User();  
         user2.setName("fengwusan");  
         user2.setPwd("123456");  
         User result =  userService.save(user);
         User result2 = userService.save(user2);
         
         userList.add(result);
         userList.add(result2);
         
         return SUCCESS;  
    }  


	

}
