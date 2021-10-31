package com.example.GrandWorldG.service;

import com.example.GrandWorldG.entity.UserInfo;

import java.util.List;

/**
 * User info interface.
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
public interface UserInfoService {

    /*
     * Get a user info by the user id.
     *
     * @param id User id.
     * @return {@link com.example.GrandWorldG.entity.UserInfo}
     */
    UserInfo getUserInfoByUserId(Long id);

    /*
     * Get info of all the users.
     *
     * @return {@link List<UserInfo>}
     */
    List<UserInfo> getAllUserInfo();

    /*
     * There are three methods for patch insertion,
     * including batch insertion in one single sql session as current implementation.
     * the other two are insertion with a for loop, and jointing a sql sentence.
     * Insertion with a for loop should be used with few inserted data.
     * Jointing a sql sentence is the worst choice. Never Use it. sql server will throw an exception when the sql length is too long.
     *
     * @param userInfoList Insert all the user info in this list.
     */
    void patchInsertUserInfo(List<UserInfo> userInfoList);

    /*
     * Insert a single user info.
     *
     * @param userInfo
     * @return {@link com.example.GrandWorldG.entity.UserInfo}
     */
    UserInfo insertUserInfo(UserInfo userInfo);
}
