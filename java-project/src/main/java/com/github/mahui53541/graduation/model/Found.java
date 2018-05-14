package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

public class Found {
    public Found() {
    }

    public Found(String foundName, String foundPicture, Boolean setFalseClaim, Date foundDatetime, Date expiryDatetime, Boolean isFound, Date foundLostDatetime, Integer userId, Integer adminId, Boolean hasReviewed, Boolean hasPassed, Boolean deleted, String foundDescription, String label) {
        this.foundName = foundName;
        this.foundPicture = foundPicture;
        this.setFalseClaim = setFalseClaim;
        this.foundDatetime = foundDatetime;
        this.expiryDatetime = expiryDatetime;
        this.isFound = isFound;
        this.foundLostDatetime = foundLostDatetime;
        this.userId = userId;
        this.adminId = adminId;
        this.hasReviewed = hasReviewed;
        this.hasPassed = hasPassed;
        this.deleted = deleted;
        this.foundDescription = foundDescription;
        this.label = label;
    }

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 拾取物品名称
     */
    @Column(name = "found_name")
    private String foundName;

    /**
     * 图片url（仅支持一张）
     */
    @Column(name = "found_picture")
    private String foundPicture;

    /**
     * 是否开启防冒领设置
     */
    @Column(name = "set_false_claim")
    private Boolean setFalseClaim;

    /**
     * 拾取时间
     */
    @Column(name = "found_datetime")
    private Date foundDatetime;

    /**
     * 拾取有效期至
     */
    @Column(name = "expiry_datetime")
    private Date expiryDatetime;

    /**
     * 失主Id
     */
    @Column(name="lost_id")
    private Integer lostId;
    /**
     * 是否找到失主
     */
    @Column(name = "is_found")
    private Boolean isFound;

    /**
     * 找到失主时间
     */
    @Column(name = "found_lost_datetime")
    private Date foundLostDatetime;

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

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 物品描述
     */
    @Column(name = "found_description")
    private String foundDescription;

    @Column(name = "label")
    private String label;

    /**
     * 提交时间
     */
    @Column(name = "submit_datetime")
    private Date submitDatetime;

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
     * 获取拾取物品名称
     *
     * @return found_name - 拾取物品名称
     */
    public String getFoundName() {
        return foundName;
    }

    /**
     * 设置拾取物品名称
     *
     * @param foundName 拾取物品名称
     */
    public void setFoundName(String foundName) {
        this.foundName = foundName == null ? null : foundName.trim();
    }

    /**
     * 获取图片url（仅支持一张）
     *
     * @return found_picture - 图片url（仅支持一张）
     */
    public String getFoundPicture() {
        return foundPicture;
    }

    /**
     * 设置图片url（仅支持一张）
     *
     * @param foundPicture 图片url（仅支持一张）
     */
    public void setFoundPicture(String foundPicture) {
        this.foundPicture = foundPicture == null ? null : foundPicture.trim();
    }

    /**
     * 获取是否开启防冒领设置
     *
     * @return set_false_claim - 是否开启防冒领设置
     */
    public Boolean getSetFalseClaim() {
        return setFalseClaim;
    }

    /**
     * 设置是否开启防冒领设置
     *
     * @param setFalseClaim 是否开启防冒领设置
     */
    public void setSetFalseClaim(Boolean setFalseClaim) {
        this.setFalseClaim = setFalseClaim;
    }

    /**
     * 获取拾取时间
     *
     * @return found_datetime - 拾取时间
     */
    public Date getFoundDatetime() {
        return foundDatetime;
    }

    /**
     * 设置拾取时间
     *
     * @param foundDatetime 拾取时间
     */
    public void setFoundDatetime(Date foundDatetime) {
        this.foundDatetime = foundDatetime;
    }

    /**
     * 获取拾取有效期至
     *
     * @return expiry_datetime - 拾取有效期至
     */
    public Date getExpiryDatetime() {
        return expiryDatetime;
    }

    /**
     * 设置拾取有效期至
     *
     * @param expiryDatetime 拾取有效期至
     */
    public void setExpiryDatetime(Date expiryDatetime) {
        this.expiryDatetime = expiryDatetime;
    }

    /**
     * 获取是否找到失主
     *
     * @return is_found - 是否找到失主
     */
    public Boolean getIsFound() {
        return isFound;
    }

    /**
     * 设置是否找到失主
     *
     * @param isFound 是否找到失主
     */
    public void setIsFound(Boolean isFound) {
        this.isFound = isFound;
    }

    /**
     * 获取找到失主时间
     *
     * @return found_lost_datetime - 找到失主时间
     */
    public Date getFoundLostDatetime() {
        return foundLostDatetime;
    }

    /**
     * 设置找到失主时间
     *
     * @param foundLostDatetime 找到失主时间
     */
    public void setFoundLostDatetime(Date foundLostDatetime) {
        this.foundLostDatetime = foundLostDatetime;
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
     * 获取物品描述
     *
     * @return found_description - 物品描述
     */
    public String getFoundDescription() {
        return foundDescription;
    }

    /**
     * 设置物品描述
     *
     * @param foundDescription 物品描述
     */
    public void setFoundDescription(String foundDescription) {
        this.foundDescription = foundDescription == null ? null : foundDescription.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public Integer getLostId() {
        return lostId;
    }

    public void setLostId(Integer lostId) {
        this.lostId = lostId;
    }
}