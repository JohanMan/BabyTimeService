package com.johan.service.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
public class TestController {

    @GetMapping("/guest/test")
    @PermitAll
    public String testGuest() {
        return "hello, Guest ..";
    }

    @GetMapping("/admin/test")
    @RolesAllowed({"USER"})
    public String testAdmin() {
        return "hello, admin ...";
    }

}
