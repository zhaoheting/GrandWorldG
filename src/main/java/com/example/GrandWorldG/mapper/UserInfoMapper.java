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

    /**
     * @param userId
     * @return com.example.GrandWorldG.entity.UserInfo
     * @author HeTing.Zhao
     * @since 2021/10/17
     **/
    UserInfo selectUserInfoByUserId(@Param("userId") Long userId);

    /**
     *
     * @return
     */
    List<UserInfo> selectAllUserInfo();

    /**
     *
     * @param userInfo
     * @return
     */
    int insertUserInfo(UserInfo userInfo);
}
