package com.example.GrandWorldG.mapper;

import com.example.GrandWorldG.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * User info mapper.
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
@Mapper
public interface UserInfoMapper {

    /*
     * Get a user info by the user id.
     *
     * @param userId
     * @return {@link com.example.GrandWorldG.entity.UserInfo}
     */
    UserInfo selectUserInfoByUserId(@Param("userId") Long userId);

    /*
     * Get info of all the users.
     *
     * @return {@link java.util.List<com.example.GrandWorldG.entity.UserInfo>}
     */
    List<UserInfo> selectAllUserInfo();

    /*
     * Insert a single user info.
     *
     * @param userInfo
     * @return {@link int}
     */
    int insertUserInfo(UserInfo userInfo);
}
