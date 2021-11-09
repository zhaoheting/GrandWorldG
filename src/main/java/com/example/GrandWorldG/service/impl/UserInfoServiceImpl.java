package com.example.GrandWorldG.service.impl;

import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.mapper.UserInfoMapper;
import com.example.GrandWorldG.service.UserInfoService;
import com.example.GrandWorldG.util.AesUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Implementation of {@link UserInfoService}.
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;
    private final SqlSessionFactory sqlSessionFactory;
    private final ConfigService configService;

    public UserInfoServiceImpl(UserInfoMapper userInfoMapper, SqlSessionFactory sqlSessionFactory,
                               ConfigService configService) {
        this.userInfoMapper = userInfoMapper;
        this.sqlSessionFactory = sqlSessionFactory;
        this.configService = configService;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long id) {
        return userInfoMapper.selectUserInfoByUserId(id);
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfoList = userInfoMapper.selectAllUserInfo();
        userInfoList.forEach(this::decryptUserInfo);
        return userInfoList;
    }

    @Override
    @Transactional
    public void patchInsertUserInfo(List<UserInfo> userInfoList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserInfoMapper patchUserInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        for (int i = 0, size = userInfoList.size(); i < size; ++i) {
            //The return value is not the amount of inserted data.
            encryptUserInfo(userInfoList.get(i));
            patchUserInfoMapper.insertUserInfo(userInfoList.get(i));
            //Commit the session every 500 amount of data in case of out of memory.
            if (i % 500 == 499) {
                sqlSession.commit();
                sqlSession.clearCache();
            }
        }
        //Commit all the remaining data.
        sqlSession.commit();
        sqlSession.clearCache();
    }

    @Override
    public UserInfo insertUserInfo(UserInfo userInfo) {
        encryptUserInfo(userInfo);
        int result = userInfoMapper.insertUserInfo(userInfo);
        if (result != 1) {
            throw new RuntimeException("Failed to create the user account.");
        }
        return userInfo;
    }

    /*
     * Encrypt essential properties of user info.
     *
     * @param userInfo
     */
    private void encryptUserInfo(UserInfo userInfo) {
        userInfo.setSocialSecurityNumber(AesUtils.encryptInCbc(userInfo.getSocialSecurityNumber(), userInfo.getDomainUsername()));
    }

    /*
     * Decrypt essential properties of user info.
     *
     * @param userInfo
     */
    private void decryptUserInfo(UserInfo userInfo) {
        userInfo.setSocialSecurityNumber(AesUtils.decryptInCbc(userInfo.getSocialSecurityNumber(), userInfo.getDomainUsername()));
    }
}
