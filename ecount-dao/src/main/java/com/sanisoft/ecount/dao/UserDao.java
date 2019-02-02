package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.User;


public interface UserDao {
	public void addUser(User user);
	public List<User> getUser();
	public User get(int id);
	public User update(User user, int id);
	public User updateCountry(User user, int id);
	public void delete(int id); 
}
