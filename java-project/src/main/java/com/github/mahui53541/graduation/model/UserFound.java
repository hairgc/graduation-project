package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_found")
public class UserFound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 失物招领ID
     */
    @Column(name = "found_id")
    private Integer foundId;

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
     * 获取失物招领ID
     *
     * @return found_id - 失物招领ID
     */
    public Integer getFoundId() {
        return foundId;
    }

    /**
     * 设置失物招领ID
     *
     * @param foundId 失物招领ID
     */
    public void setFoundId(Integer foundId) {
        this.foundId = foundId;
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
     * @return submit_datetime
     */
    public Date getSubmitDatetime() {
        return submitDatetime;
    }

    /**
     * @param submitDatetime
     */
    public void setSubmitDatetime(Date submitDatetime) {
        this.submitDatetime = submitDatetime;
    }
}