package com.example.GrandWorldG.controller;

import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
@RestController
public class UserInfoController {
    @Autowired
    public UserInfoService userInfoService;

    @GetMapping("/user/{userId}")
    public UserInfo getUserById(@PathVariable("userId") Long userId) {
        return userInfoService.getUserInfoByUserId(userId);
    }
}
