package com.johan.service.member.controller;

import com.johan.common.entity.ResultBody;
import com.johan.service.member.entity.User;
import com.johan.service.member.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("添加后台管理员")
    @PostMapping("insertBgAdmin")
    public ResultBody insertBgAdmin(@ApiParam("管理员后台信息") @RequestBody User user) {
        userService.insertBgAdmin(user);
        return ResultBody.ok();
    }

    @ApiOperation("添加普通用户")
    @PostMapping("insertUser")
    public ResultBody insertUser(@ApiParam("用户信息") @RequestBody User user) {
        userService.insertUser(user);
        return ResultBody.ok();
    }

}
