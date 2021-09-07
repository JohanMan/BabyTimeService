package com.johan.service.member.controller;

import com.johan.common.entity.ResultBody;
import com.johan.common.utils.JwtTokenUtil;
import com.johan.common.utils.RedisUtil;
import com.johan.service.member.entity.User;
import com.johan.service.member.entity.request.LoginData;
import com.johan.service.member.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api("用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("添加后台管理员")
    @PostMapping("/insertBgAdmin")
    public ResultBody insertBgAdmin(@ApiParam("管理员后台信息") @RequestBody User user) {
        userService.insertBgAdmin(user);
        return ResultBody.ok();
    }

    @ApiOperation("添加普通用户")
    @PostMapping("/insertUser")
    public ResultBody insertUser(@ApiParam("用户信息") @RequestBody User user) {
        userService.insertUser(user);
        return ResultBody.ok();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultBody login(@RequestBody LoginData loginData, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginData.getUsername());
        if (userDetails == null) {
            return ResultBody.error("用户名/密码错误");
        }
        if (!passwordEncoder.matches(loginData.getPassword(), userDetails.getPassword())) {
            return ResultBody.error("用户名/密码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.createToken(userDetails.getUsername(), StringUtils.join(userDetails.getAuthorities(), ","));
        return ResultBody.ok().setData(jwtTokenUtil.getHeader(), token);
    }

    @GetMapping("/test")
    public ResultBody test() {
        return ResultBody.ok();
    }

}
