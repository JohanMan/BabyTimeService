package com.johan.service.member.service;

import com.johan.service.member.entity.User;
import com.johan.service.member.entity.request.LoginRequestData;
import com.johan.service.member.entity.response.LoginRespondData;

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

    /**
     * 登录
     * @param data 登录参数
     * @return 结果
     */
    LoginRespondData login(LoginRequestData data);

}
