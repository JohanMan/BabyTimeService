package com.johan.service.member.service;

import com.johan.service.member.entity.Role;

public interface RoleService {

    // 管理员
    int ROLE_ADMIN = 1;
    // 后台管理员
    int ROLE_BG_ADMIN = 2;
    // VIP会员
    int ROLE_VIP = 3;
    // 会员
    int ROLE_USER = 4;

    /**
     * 通过用户ID查找角色
     * @param userId 用户ID
     * @return 角色
     */
    Role findByUserId(String userId);

}
