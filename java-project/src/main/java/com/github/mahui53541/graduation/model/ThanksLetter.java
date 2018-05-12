package com.github.mahui53541.graduation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "thanks_letter")
public class ThanksLetter {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 感谢人Id
     */
    @Column(name = "editor_id")
    private Integer editorId;

    /**
     * 标题
     */
    private String title;

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

    /**
     * 提交时间
     */
    @Column(name = "submit_datetime")
    private Date submitDatetime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 内容
     */
    private String content;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}