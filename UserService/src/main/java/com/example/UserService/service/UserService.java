package com.example.UserService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserService.dao.UserMapper;
import com.example.UserService.entity.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User findUserByName(String _username) {
		User user = userMapper.selectByPrimaryKey(_username);
		if (user == null)
			return new User();
		else
			return user;
	}

	public boolean addUser(User u) {
		boolean result = false;
		try {
			userMapper.insert(u);
			result = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}

	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	public boolean updateUser(User u) {
		boolean result = false;
		try {
			int rowsAffected = userMapper.updateByPrimaryKey(u);
			result = rowsAffected > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public boolean deleteUser(String _username) {
		boolean result = false;
		try {
			userMapper.deleteByPrimaryKey(_username);
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}

}