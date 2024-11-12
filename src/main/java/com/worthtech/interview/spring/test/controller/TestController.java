package com.worthtech.interview.spring.test.controller;

import com.worthtech.interview.spring.test.dto.UserDetails;
import com.worthtech.interview.spring.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping("/userData")
    public UserDetails[] getUserData() {
        return userService.getUserData();
    }

}
