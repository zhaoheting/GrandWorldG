package com.example.GrandWorldG.service.impl;

import com.example.GrandWorldG.constants.CommonConstants;
import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.mapper.UserInfoMapper;
import com.example.GrandWorldG.service.UserInfoService;
import com.example.GrandWorldG.util.AesUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;

    private final SqlSessionFactory sqlSessionFactory;

    public UserInfoServiceImpl(UserInfoMapper userInfoMapper, SqlSessionFactory sqlSessionFactory) {
        this.userInfoMapper = userInfoMapper;
        this.sqlSessionFactory = sqlSessionFactory;
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
        //Commit all the left data.
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

    /**
     * Build a user info list for testing batch insertion.
     *
     * @return
     */
    private List<UserInfo> buildUserInfoList() {
        List<UserInfo> insertedUserInfoList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            UserInfo userInfo = new UserInfo();
            //Generated username randomly.
            String username = (char) (random.nextInt(25) + 97) + String.valueOf(random.nextInt(100)) + (char) (random.nextInt(25) + 97);
            userInfo.setUsername(username);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            userInfo.setCreateTime(currentTime);
            userInfo.setPassword(username);
            userInfo.setLastUpdateTime(currentTime);
            userInfo.setDomainUsername(CommonConstants.DOMAIN_USERNAME);
            insertedUserInfoList.add(userInfo);
        }
        return insertedUserInfoList;
    }

    private void encryptUserInfo(UserInfo userInfo) {
        userInfo.setSocialSecurityNumber(AesUtils.encryptInCbc(userInfo.getSocialSecurityNumber()));
    }

    private void decryptUserInfo(UserInfo userInfo) {
        userInfo.setSocialSecurityNumber(AesUtils.decryptInCbc(userInfo.getSocialSecurityNumber()));
    }
}
