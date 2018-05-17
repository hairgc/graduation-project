package com.github.mahui53541.graduation.vo;

import com.github.mahui53541.graduation.model.Found;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/17 10:59
 * @Version: 1.0
 */
public class FoundAdminVO extends Found {
    /**
     * 上传人姓名
     */
    private String userName;

    /**
     * adminName
     */
    private String adminName;

    /**
     * 失主姓名
     */
    private String lostName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getLostName() {
        return lostName;
    }

    public void setLostName(String lostName) {
        this.lostName = lostName;
    }
}
