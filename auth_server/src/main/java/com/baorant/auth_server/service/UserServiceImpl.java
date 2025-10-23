package com.baorant.auth_server.service;

import com.baorant.auth_server.dao.SpringcloudUserMapper;
import com.baorant.auth_server.dao.SpringcloudUserRoleMapper;
import com.baorant.auth_server.entity.SpringcloudUser;
import com.baorant.auth_server.entity.SpringcloudUserRole;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private SpringcloudUserMapper springcloudUserMapper;
    @Autowired
    private SpringcloudUserRoleMapper springcloudUserRoleMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public SpringcloudUser getUser(String username) {
        return springcloudUserMapper.findByUsername(username);
    }

    @Override
    public SpringcloudUser getUserWithRoles(String username) {
        SpringcloudUser user = springcloudUserMapper.findByUsername(username);
        if (user != null) {
            // 查询用户角色，不依赖role表
            List<SpringcloudUserRole> roles = jdbcTemplate.query(
                "SELECT * FROM springcloud_user_role WHERE user_id = ?",
                new Object[]{user.getId()},
                (rs, rowNum) -> {
                    SpringcloudUserRole role = new SpringcloudUserRole();
                    role.setId(rs.getInt("id"));
                    role.setUserId(rs.getInt("user_id"));
                    role.setRoleId(rs.getInt("role_id"));
                    
                    // 根据role_id设置角色名称
                    int roleId = rs.getInt("role_id");
                    if (roleId == 1) {
                        role.setName("ADMIN");
                    } else if (roleId == 2) {
                        role.setName("USER");
                    } else {
                        role.setName("USER"); // 默认角色
                    }
                    
                    return role;
                }
            );
            
            // 如果没有角色，给用户分配默认的USER角色
            if (roles.isEmpty()) {
                SpringcloudUserRole defaultRole = new SpringcloudUserRole();
                defaultRole.setId(999);
                defaultRole.setUserId(user.getId());
                defaultRole.setRoleId(2);
                defaultRole.setName("USER");
                roles.add(defaultRole);
            }
            
            user.setRoles(new java.util.HashSet<>(roles));
        }
        return user;
    }

}
