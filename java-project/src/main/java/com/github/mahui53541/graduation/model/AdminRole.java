package com.github.mahui53541.graduation.model;

import javax.persistence.*;

@Table(name = "admin_role")
public class AdminRole {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 管理员ID
     */
    @Column(name = "admin_id")
    private Integer adminId;

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
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取管理员ID
     *
     * @return admin_id - 管理员ID
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员ID
     *
     * @param adminId 管理员ID
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}