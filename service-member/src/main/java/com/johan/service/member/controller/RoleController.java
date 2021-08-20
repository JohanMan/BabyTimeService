package com.johan.service.member.controller;

import com.johan.service.member.entity.Role;
import com.johan.service.member.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("/add")
    public String addRole(@RequestBody Role role) {
        roleService.insert(role);
        return "success";
    }

}
