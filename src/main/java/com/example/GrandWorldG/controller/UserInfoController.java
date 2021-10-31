package com.example.GrandWorldG.controller;

import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.model.PageableModel;
import com.example.GrandWorldG.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link UserInfo} controller.
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

    @GetMapping("/users")
    public PageInfo<UserInfo> getAllUserInfoInPage(PageableModel<UserInfo> pageableModel) {
        PageHelper.startPage(pageableModel.getPageNum(), pageableModel.getPageSize());
        List<UserInfo> userInfoList = userInfoService.getAllUserInfo();
        return new PageInfo<>(userInfoList);
    }

    @PostMapping("/usersInsertion")
    public void patchInsertUserInfo(@RequestBody List<UserInfo> userInfoList) {
        userInfoService.patchInsertUserInfo(userInfoList);
    }

    @PostMapping("/userInsertion")
    public UserInfo insertUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.insertUserInfo(userInfo);
    }
}
