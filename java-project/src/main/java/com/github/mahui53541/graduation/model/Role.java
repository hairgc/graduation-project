package com.github.mahui53541.graduation.model;

import javax.persistence.*;

public class Role {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 角色编号
     */
    @Column(name = "role_sn")
    private String roleSn;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

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
     * 获取角色编号
     *
     * @return role_sn - 角色编号
     */
    public String getRoleSn() {
        return roleSn;
    }

    /**
     * 设置角色编号
     *
     * @param roleSn 角色编号
     */
    public void setRoleSn(String roleSn) {
        this.roleSn = roleSn == null ? null : roleSn.trim();
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}