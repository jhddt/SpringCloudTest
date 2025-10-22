package com.baorant.auth_server.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * 对应数据库表 springcloud_user
 * 包含用户的基本信息以及关联的角色信息
 */
@ApiModel(description = "用户信息及关联的角色信息")
public class SpringcloudUser{
    /**
     * 用户ID，主键
     */
    @ApiModelProperty(value="用户ID")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
     * 邮箱地址
     */
    @ApiModelProperty(value="邮箱地址")
    private String email;

    /**
     * 账户创建时间
     */
    @ApiModelProperty(value="账户创建时间")
    private Date createTime;

    /**
     * 用户关联的角色集合
     */
    @ApiModelProperty(value="用户关联的角色集合")
    private Set<SpringcloudUserRole> roles = new HashSet<SpringcloudUserRole>();

    /**
     * 获取用户ID
     *
     * @return id 用户ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码
     *
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取邮箱地址
     *
     * @return email 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     *
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取账户创建时间
     *
     * @return createTime 账户创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置账户创建时间
     *
     * @param createTime 账户创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用户关联的角色集合
     * 
     * @return Set<SpringcloudUserRole> 角色集合
     */
    public Set<SpringcloudUserRole> getRoles() {
        return roles;
    }

    /**
     * 设置用户关联的角色集合
     * 
     * @param roles 角色集合
     */
    public void setRoles(Set<SpringcloudUserRole> roles) {
        this.roles = roles;
    }
}