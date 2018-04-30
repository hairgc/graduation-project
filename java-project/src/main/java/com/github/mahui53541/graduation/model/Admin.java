package com.github.mahui53541.graduation.model;

import javax.persistence.*;

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 用户名（学号）
     */
    private String username;

    private String password;

    /**
     * 0:男1：女
     */
    private Boolean sex;

    /**
     * 手机
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 用户昵称
     */
    private String nickname;

    private Boolean deleted;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
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
     * 获取手机
     *
     * @return phone_number - 手机
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机
     *
     * @param phoneNumber 手机
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 获取用户昵称
     *
     * @return nickname - 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称
     *
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}