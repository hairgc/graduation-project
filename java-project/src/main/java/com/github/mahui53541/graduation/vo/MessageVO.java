package com.github.mahui53541.graduation.vo;

import java.util.Date;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/9 13:01
 * @Version: 1.0
 */
public class MessageVO {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 失物招领/寻物启事ID
     */
    private Integer foreignId;

    /**
     * 发送人ID
     */
    private Integer userId;

    /**
     * 发送人手机号
     */
    private String phoneNumber;

    /**
     * 发送人用户姓名
     */
    private String nickname;


    /**
     * 是否已经沟通确认
     */
    private Boolean isConfirm;

    /**
     * 提交时间
     */
    private Date submitDatetime;

    /**
     * 留言
     */
    private String content;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 类型 1：失物招领 2：寻物启事
     */
    private Integer type;

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
     * 获取失物招领ID
     *
     * @return found_id - 失物招领ID
     */
    public Integer getForeignId() {
        return foreignId;
    }

    /**
     * 设置失物招领ID
     *
     * @param foreignId 失物招领ID
     */
    public void setForeignId(Integer foreignId) {
        this.foreignId = foreignId;
    }

    /**
     * 获取失主ID
     *
     * @return user_id - 失主ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置失主ID
     *
     * @param userId 失主ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取是否已经沟通确认
     *
     * @return is_confirm - 是否已经沟通确认
     */
    public Boolean getIsConfirm() {
        return isConfirm;
    }

    /**
     * 设置是否已经沟通确认
     *
     * @param isConfirm 是否已经沟通确认
     */
    public void setIsConfirm(Boolean isConfirm) {
        this.isConfirm = isConfirm;
    }

    /**
     * 获取提交时间
     *
     * @return submit_datetime - 提交时间
     */
    public Date getSubmitDatetime() {
        return submitDatetime;
    }

    /**
     * 设置提交时间
     *
     * @param submitDatetime 提交时间
     */
    public void setSubmitDatetime(Date submitDatetime) {
        this.submitDatetime = submitDatetime;
    }

    /**
     * 获取留言
     *
     * @return content - 留言
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置留言
     *
     * @param content 留言
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取是否已读
     *
     * @return is_read - 是否已读
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     * 设置是否已读
     *
     * @param isRead 是否已读
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
