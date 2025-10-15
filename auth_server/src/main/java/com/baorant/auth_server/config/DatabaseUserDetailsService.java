package com.baorant.auth_server.config;

import com.baorant.auth_server.entity.SpringcloudUser;
import com.baorant.auth_server.entity.SpringcloudUserRole;
import com.baorant.auth_server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUserDetailsService.class);
    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user from database: {}", username);
        
        SpringcloudUser user = userService.getUserWithRoles(username);
        if (user == null || user.getId() == null || user.getId() < 1) {
            logger.warn("User not found in database: {}", username);
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        
        logger.info("User found: {}, roles: {}", user.getUsername(), user.getRoles());
        
        return new User(
                user.getUsername(), 
                user.getPassword(), 
                true, // enabled
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                getGrantedAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(SpringcloudUser user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        if (user.getRoles() != null) {
            for (SpringcloudUserRole role : user.getRoles()) {
                if (role.getName() != null && !role.getName().trim().isEmpty()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
                }
            }
        }
        
        // 如果没有角色，添加默认的USER角色
        if (authorities.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        
        logger.info("Granted authorities for user {}: {}", user.getUsername(), authorities);
        return authorities;
    }
}
