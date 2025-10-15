package com.baorant.userservice.service;

import com.baorant.userservice.dao.UserMapper;
import com.baorant.userservice.entity.User;
import com.baorant.userservice.entity.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

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
            logger.debug(u.getName(), u.getUsername(), u.getPassword());
            userMapper.insert(u);
            result = true;
        } catch (Exception ex) {
            logger.debug(ex.toString());
        }
        return result;
    }

    public List<User> selectAll() {
        return userMapper.selectByExample(new UserExample());
    }

    public boolean updateUser(User u) {
        boolean result = false;
        try {
            userMapper.updateByPrimaryKey(u);
            result = true;
        } catch (Exception ex) {
            logger.debug(ex.toString());
        }
        return result;
    }

    public boolean deleteUser(String _username) {
        boolean result = false;
        try {
            userMapper.deleteByPrimaryKey(_username);
            result = true;
        } catch (Exception ex) {
            logger.debug(ex.toString());
        }
        return result;
    }
}

