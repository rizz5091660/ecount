package com.sanisoft.ecount.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanisoft.ecount.dao.UserDao;
import com.sanisoft.ecount.entity.User;
import com.sanisoft.ecount.ws.service.UserService;


@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;

	public List<User> getUser() {
		return userDao.getUser();
	}

	public User get(int id) {
		return userDao.get(id);
	}

	public void createUser(User user) {
		userDao.addUser(user);
	} 

	public void deleteUserById(int id) {
		userDao.delete(id);
	}
	
	@Override
	public User updatePartially(User user, int id) {
		userDao.updateCountry(user,id);
		return userDao.get(id);
	}

	@Override
	public User update(User user,int id) {
		return userDao.update(user, id);
	}

}
