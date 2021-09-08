package com.johan.service.member.service.impl;

import com.johan.common.entity.GlobalException;
import com.johan.common.utils.JwtTokenUtil;
import com.johan.common.utils.RedisUtil;
import com.johan.common.utils.SnowFlake;
import com.johan.service.member.dao.UserMapper;
import com.johan.service.member.entity.User;
import com.johan.service.member.entity.request.LoginRequestData;
import com.johan.service.member.entity.response.LoginRespondData;
import com.johan.service.member.security.WebUserDetail;
import com.johan.service.member.service.RoleService;
import com.johan.service.member.service.UserRoleService;
import com.johan.service.member.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Transactional
    @Override
    public void insertBgAdmin(User user) {
        insert(user, RoleService.ROLE_BG_ADMIN);
    }

    @Transactional
    @Override
    public void insertUser(User user) {
        insert(user, RoleService.ROLE_USER);
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @param roleId 角色ID
     */
    @Transactional
    public void insert(User user, int roleId) {
        // 创建用户ID
        String userId = String.valueOf(snowFlake.nextId());
        user.setUid(userId);
        // 添加用户
        int row = userMapper.insertSelective(user);
        if (row <= 0) {
            throw new GlobalException("添加用户失败");
        }
        // 添加用户角色
        row = userRoleService.insert(userId, roleId);
        if (row <= 0) {
            throw new GlobalException("添加用户失败");
        }
    }

    @Override
    public User findByMobile(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Override
    public LoginRespondData login(LoginRequestData requestData) {
        WebUserDetail userDetails = (WebUserDetail) userDetailsService.loadUserByUsername(requestData.getUsername());
        if (userDetails == null) {
            throw new GlobalException("用户名/密码错误");
        }
        if (!passwordEncoder.matches(requestData.getPassword(), userDetails.getPassword())) {
            throw new GlobalException("用户名/密码错误");
        }
        // 检验后清除密码
        userDetails.getUser().setPassword("");
        // 返回数据
        String token = jwtTokenUtil.createToken(userDetails.getUsername(), StringUtils.join(userDetails.getAuthorities(), ","));
        LoginRespondData respondData = new LoginRespondData();
        respondData.setToken(token);
        respondData.setUser(userDetails.getUser());
        respondData.setRole(userDetails.getRole());
        // 保存到Redis
        redisUtil.set(token, respondData);
        return respondData;
    }

}
