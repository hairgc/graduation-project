package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_lost")
public class UserLost {
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
}