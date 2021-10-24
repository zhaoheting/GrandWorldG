package com.example.GrandWorldG.service;

import com.example.GrandWorldG.entity.UserInfo;

import java.util.List;

/**
 * TODO
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
public interface UserInfoService {
    UserInfo getUserInfoByUserId(Long id);

    List<UserInfo> getAllUserInfo();

    void patchInsertUserInfo();

    UserInfo insertUserInfo(UserInfo userInfo);
}
