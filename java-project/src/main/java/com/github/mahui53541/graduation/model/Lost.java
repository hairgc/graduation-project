package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

public class Lost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 丢失物品名称或标题
     */
    @Column(name = "lost_name")
    private String lostName;

    /**
     * 图片url（仅支持一张）
     */
    @Column(name = "lost_picture")
    private String lostPicture;

    /**
     * 丢失时间
     */
    @Column(name = "lost_datetime")
    private Date lostDatetime;

    /**
     * 寻物启事有效期至
     */
    @Column(name = "expiry_datetime")
    private Date expiryDatetime;

    /**
     * 是否找到失物
     */
    @Column(name = "is_found")
    private Boolean isFound;

    /**
     * 找到时间
     */
    @Column(name = "found_datetime")
    private Date foundDatetime;

    /**
     * 发布者ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 审核者Id
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 是否被审核
     */
    @Column(name = "has_reviewed")
    private Boolean hasReviewed;

    /**
     * 是否通过审核
     */
    @Column(name = "has_passed")
    private Boolean hasPassed;

    private Boolean deleted;

    /**
     * 详细描述
     */
    @Column(name = "lost_description")
    private String lostDescription;

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
     * 获取丢失物品名称或标题
     *
     * @return lost_name - 丢失物品名称或标题
     */
    public String getLostName() {
        return lostName;
    }

    /**
     * 设置丢失物品名称或标题
     *
     * @param lostName 丢失物品名称或标题
     */
    public void setLostName(String lostName) {
        this.lostName = lostName == null ? null : lostName.trim();
    }

    /**
     * 获取图片url（仅支持一张）
     *
     * @return lost_picture - 图片url（仅支持一张）
     */
    public String getLostPicture() {
        return lostPicture;
    }

    /**
     * 设置图片url（仅支持一张）
     *
     * @param lostPicture 图片url（仅支持一张）
     */
    public void setLostPicture(String lostPicture) {
        this.lostPicture = lostPicture == null ? null : lostPicture.trim();
    }

    /**
     * 获取丢失时间
     *
     * @return lost_datetime - 丢失时间
     */
    public Date getLostDatetime() {
        return lostDatetime;
    }

    /**
     * 设置丢失时间
     *
     * @param lostDatetime 丢失时间
     */
    public void setLostDatetime(Date lostDatetime) {
        this.lostDatetime = lostDatetime;
    }

    /**
     * 获取寻物启事有效期至
     *
     * @return expiry_datetime - 寻物启事有效期至
     */
    public Date getExpiryDatetime() {
        return expiryDatetime;
    }

    /**
     * 设置寻物启事有效期至
     *
     * @param expiryDatetime 寻物启事有效期至
     */
    public void setExpiryDatetime(Date expiryDatetime) {
        this.expiryDatetime = expiryDatetime;
    }

    /**
     * 获取是否找到失物
     *
     * @return is_found - 是否找到失物
     */
    public Boolean getIsFound() {
        return isFound;
    }

    /**
     * 设置是否找到失物
     *
     * @param isFound 是否找到失物
     */
    public void setIsFound(Boolean isFound) {
        this.isFound = isFound;
    }

    /**
     * 获取找到时间
     *
     * @return found_datetime - 找到时间
     */
    public Date getFoundDatetime() {
        return foundDatetime;
    }

    /**
     * 设置找到时间
     *
     * @param foundDatetime 找到时间
     */
    public void setFoundDatetime(Date foundDatetime) {
        this.foundDatetime = foundDatetime;
    }

    /**
     * 获取发布者ID
     *
     * @return user_id - 发布者ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置发布者ID
     *
     * @param userId 发布者ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取审核者Id
     *
     * @return admin_id - 审核者Id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置审核者Id
     *
     * @param adminId 审核者Id
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取是否被审核
     *
     * @return has_reviewed - 是否被审核
     */
    public Boolean getHasReviewed() {
        return hasReviewed;
    }

    /**
     * 设置是否被审核
     *
     * @param hasReviewed 是否被审核
     */
    public void setHasReviewed(Boolean hasReviewed) {
        this.hasReviewed = hasReviewed;
    }

    /**
     * 获取是否通过审核
     *
     * @return has_passed - 是否通过审核
     */
    public Boolean getHasPassed() {
        return hasPassed;
    }

    /**
     * 设置是否通过审核
     *
     * @param hasPassed 是否通过审核
     */
    public void setHasPassed(Boolean hasPassed) {
        this.hasPassed = hasPassed;
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

    /**
     * 获取详细描述
     *
     * @return lost_description - 详细描述
     */
    public String getLostDescription() {
        return lostDescription;
    }

    /**
     * 设置详细描述
     *
     * @param lostDescription 详细描述
     */
    public void setLostDescription(String lostDescription) {
        this.lostDescription = lostDescription == null ? null : lostDescription.trim();
    }
}