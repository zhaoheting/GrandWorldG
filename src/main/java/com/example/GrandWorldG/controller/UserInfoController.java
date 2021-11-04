package com.example.GrandWorldG.controller;

import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.model.PageableModel;
import com.example.GrandWorldG.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link UserInfo} controller.
 * Cross origin annotation is used to solve cross domain issues. It is effective than setting headers for response.
 * But we need to add this annotation for every controller.
 * There is a better approach that override "addCorsMappings‘ method of interface "WebMvcConfigurer".
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
@RestController
//@CrossOrigin(value = "http://localhost:4200")
public class UserInfoController {
    @Autowired
    public UserInfoService userInfoService;

    @GetMapping("/user/{userId}")
    public UserInfo getUserById(@PathVariable("userId") Long userId) {
        return userInfoService.getUserInfoByUserId(userId);
    }

    @GetMapping("/users")
    public ResponseEntity<PageInfo<UserInfo>> getAllUserInfoInPage(PageableModel<UserInfo> pageableModel) {
        PageHelper.startPage(pageableModel.getPageNum(), pageableModel.getPageSize());
        List<UserInfo> userInfoList = userInfoService.getAllUserInfo();
        PageInfo<UserInfo> pageInfo = new PageInfo(userInfoList);
        return ResponseEntity.ok().body(pageInfo);
    }

    @PostMapping("/usersInsertion")
    public void patchInsertUserInfo(@RequestBody List<UserInfo> userInfoList) {
        userInfoService.patchInsertUserInfo(userInfoList);
    }

    @PostMapping("/userInsertion")
    public ResponseEntity<UserInfo> insertUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo insertedUser = userInfoService.insertUserInfo(userInfo);
        return ResponseEntity.ok().body(insertedUser);
    }
}
