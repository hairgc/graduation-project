package com.github.mahui53541.graduation.vo;

import com.github.mahui53541.graduation.model.Lost;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/13 13:01
 * @Version: 1.0
 */
public class LostUserVO extends Lost{
    /**
     * 用户名（学号）
     */
    private String username;

    /**
     * 用户姓名
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phoneNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
