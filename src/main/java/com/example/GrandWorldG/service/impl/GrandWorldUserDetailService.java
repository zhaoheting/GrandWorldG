package com.example.GrandWorldG.service.impl;

import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author Hobbs.Heting.Zhao
 * @since 11/15/2021
 */
public class GrandWorldUserDetailService implements UserDetailsService {
    @Autowired
    public UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUsername(username);
        if(userInfo == null){
            throw new RuntimeException("Can not find current user.");
        }
        return userInfo;
    }
}
