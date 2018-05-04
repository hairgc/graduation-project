package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 用户名（学号）
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 0:男1：女
     */
    private Boolean sex;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 用户姓名
     */
    private String nickname;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名（学号）
     *
     * @return username - 用户名（学号）
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名（学号）
     *
     * @param username 用户名（学号）
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
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
     * 获取0:男1：女
     *
     * @return sex - 0:男1：女
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置0:男1：女
     *
     * @param sex 0:男1：女
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 获取用户姓名
     *
     * @return nickname - 用户姓名
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户姓名
     *
     * @param nickname 用户姓名
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取是否删除
     *
     * @return deleted - 是否删除
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除
     *
     * @param deleted 是否删除
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}