package com.johan.service.member.service;

import com.johan.service.member.entity.User;

public interface UserService {

    /**
     * 添加后台管理员
     * @param user 后台管理员信息
     */
    void insertBgAdmin(User user);

    /**
     * 添加普通用户
     * @param user 用户信息
     */
    void insertUser(User user);

    /**
     * 通过手机号查找用户
     * @param mobile 手机号
     * @return 用户信息
     */
    User findByMobile(String mobile);

}
