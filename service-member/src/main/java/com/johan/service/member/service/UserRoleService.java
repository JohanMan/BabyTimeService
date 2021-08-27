package com.johan.service.member.service;

public interface UserRoleService {

    /**
     * 添加
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    int insert(String userId, int roleId);

}
