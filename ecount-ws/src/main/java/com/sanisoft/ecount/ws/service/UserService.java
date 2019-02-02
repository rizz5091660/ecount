package com.sanisoft.ecount.ws.service;

import java.util.List;

import com.sanisoft.ecount.entity.User;



public interface UserService {
	public void createUser(User user);
	public List<User> getUser();
	public User get(int id); 
	public User update(User user, int id);
	public void deleteUserById(int id);
	public User updatePartially(User user, int id);
}
