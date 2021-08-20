package com.johan.service.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试Mysql接口")
@RestController
@RequestMapping("/mysql")
public class MysqlController {

    @ApiOperation("测试添加角色")
    @GetMapping("/insertRole")
    public String test() {
        return "test ------------ ";
    }

}
