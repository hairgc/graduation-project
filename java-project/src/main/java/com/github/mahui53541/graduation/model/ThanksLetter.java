package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "thanks_letter")
public class ThanksLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 感谢人Id
     */
    @Id
    @Column(name = "editor_id")
    private Integer editorId;

    /**
     * 被感谢人Id
     */
    @Column(name = "thanks_id")
    private Integer thanksId;

    /**
     * 寻物启事Id
     */
    @Column(name = "lost_id")
    private Integer lostId;

    /**
     * 失物招领ID
     */
    @Column(name = "found_id")
    private Integer foundId;

    @Column(name = "submit_datetime")
    private Date submitDatetime;

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
     * 获取感谢人Id
     *
     * @return editor_id - 感谢人Id
     */
    public Integer getEditorId() {
        return editorId;
    }

    /**
     * 设置感谢人Id
     *
     * @param editorId 感谢人Id
     */
    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    /**
     * 获取被感谢人Id
     *
     * @return thanks_id - 被感谢人Id
     */
    public Integer getThanksId() {
        return thanksId;
    }

    /**
     * 设置被感谢人Id
     *
     * @param thanksId 被感谢人Id
     */
    public void setThanksId(Integer thanksId) {
        this.thanksId = thanksId;
    }

    /**
     * 获取寻物启事Id
     *
     * @return lost_id - 寻物启事Id
     */
    public Integer getLostId() {
        return lostId;
    }

    /**
     * 设置寻物启事Id
     *
     * @param lostId 寻物启事Id
     */
    public void setLostId(Integer lostId) {
        this.lostId = lostId;
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