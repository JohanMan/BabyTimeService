package com.johan.service.member.entity;

import java.util.Date;

/**
 * 角色表
 *
 * @author Johan
 * @date 2021/08/27
 */
public class Role {
    /**
     * 角色id
     */
    private Integer rid;

    /**
     * 角色等级
     */
    private Byte roleLevel;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Byte getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Byte roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}