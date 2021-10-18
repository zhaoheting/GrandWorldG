package com.example.GrandWorldG.service.impl;

import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.mapper.UserInfoMapper;
import com.example.GrandWorldG.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;

    public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long id) {
        return userInfoMapper.selectUserInfoByUserId(id);
    }
}
