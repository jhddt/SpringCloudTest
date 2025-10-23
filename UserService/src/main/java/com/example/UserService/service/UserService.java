package com.example.UserService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserService.dao.UserMapper;
import com.example.UserService.entity.User;

/**
 * UserService类提供了对用户数据的基本操作，包括增删改查功能
 * 该类使用Spring框架的注解进行依赖注入和组件管理
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 根据用户名查找用户信息
	 * @param _username 用户名
	 * @return 如果找到用户则返回用户对象，否则返回空的用户对象
	 */
	public User findUserByName(String _username) {
		User user = userMapper.selectByPrimaryKey(_username);
		if (user == null)
			return new User();
		else
			return user;
	}

	/**
	 * 添加新用户
	 * @param u 待添加的用户对象
	 * @return 添加成功返回true，否则返回false
	 */
	public boolean addUser(User u) {
		// 尝试插入用户数据，捕获可能的异常
		boolean result = false;
		try {
			userMapper.insert(u);
			result = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}

	/**
	 * 查询所有用户
	 * @return 包含所有用户的列表
	 */
	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	/**
	 * 更新用户信息
	 * @param u 包含更新信息的用户对象
	 * @return 更新成功返回true，否则返回false
	 */
	public boolean updateUser(User u) {
		// 尝试更新用户数据，通过影响行数判断是否成功
		boolean result = false;
		try {
			int rowsAffected = userMapper.updateByPrimaryKey(u);
			result = rowsAffected > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据用户名删除用户
	 * @param _username 待删除的用户名
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean deleteUser(String _username) {
		// 尝试删除用户数据，忽略异常但返回操作结果
		boolean result = false;
		try {
			userMapper.deleteByPrimaryKey(_username);
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}

}