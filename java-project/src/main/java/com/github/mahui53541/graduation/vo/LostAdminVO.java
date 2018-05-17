package com.github.mahui53541.graduation.vo;

import com.github.mahui53541.graduation.model.Lost;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/17 13:14
 * @Version: 1.0
 */
public class LostAdminVO extends Lost {
    /**
     * 上传人姓名
     */
    private String userName;

    /**
     * adminName
     */
    private String adminName;

    /**
     * 拾取人姓名
     */
    private String foundName;

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

    public String getFoundName() {
        return foundName;
    }

    public void setFoundName(String foundName) {
        this.foundName = foundName;
    }
}
