package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_lost")
public class UserLost {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 寻物启事ID
     */
    @Column(name = "lost_id")
    private Integer lostId;

    /**
     * 失主ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 是否已经沟通确认
     */
    @Column(name = "is_confirm")
    private Boolean isConfirm;

    /**
     * 提交时间
     */
    @Column(name = "submit_datetime")
    private Date submitDatetime;

    /**
     * 留言
     */
    private String content;

    /**
     * 是否已读
     */
    @Column(name = "is_read")
    private Boolean isRead;

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
     * 获取寻物启事ID
     *
     * @return lost_id - 寻物启事ID
     */
    public Integer getLostId() {
        return lostId;
    }

    /**
     * 设置寻物启事ID
     *
     * @param lostId 寻物启事ID
     */
    public void setLostId(Integer lostId) {
        this.lostId = lostId;
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
}