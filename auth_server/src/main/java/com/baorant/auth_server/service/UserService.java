package com.baorant.auth_server.service;


import com.baorant.auth_server.entity.SpringcloudUser;

public interface UserService {
    SpringcloudUser getUser(String username);
    SpringcloudUser getUserWithRoles(String username);
}