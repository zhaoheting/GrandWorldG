package com.example.GrandWorldG.service.impl;

import com.example.GrandWorldG.entity.UserInfo;
import com.example.GrandWorldG.mapper.UserInfoMapper;
import com.example.GrandWorldG.service.UserInfoService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
    @Transactional
    /**
     * There are three methods for patch insertion,
     * including batch insertion in one single sql session as below.
     * the other two are insertion with a for loop, and jointing a sql sentence.
     * Insertion with a for loop should be used with few inserted data.
     * Jointing a sql sentence is the worst choice. Never Use it. sql server will throw an exception when the sql length is too long.
     */
    public Long patchInsertUserInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserInfoMapper patchUserInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        List<UserInfo> insertedUserInfoList = buildUserInfoList();
        long insertedCount = 0L;
        for (int i = 0, size = insertedUserInfoList.size(); i < size; ++i) {
            insertedCount += patchUserInfoMapper.insertUserInfo(insertedUserInfoList.get(i));
            //Commit the session every 500 amount of data in case of out of memory.
            if (i % 500 == 499) {
                sqlSession.commit();
                sqlSession.clearCache();
            }
        }
        //Commit all the left data.
        sqlSession.commit();
        sqlSession.clearCache();
        return insertedCount;
    }

    /**
     * Build a user info list for testing batch insertion.
     *
     * @return
     */
    private List<UserInfo> buildUserInfoList() {
        List<UserInfo> insertedUserInfoList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; ++i) {
            UserInfo userInfo = new UserInfo();
            //Generated username randomly.
            userInfo.setUsername((char) (random.nextInt(25) + 97) + "" + (char) (random.nextInt(25) + 97));
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            userInfo.setCreateTime(currentTime);
            userInfo.setPassword(UUID.randomUUID().toString());
            userInfo.setLastUpdateTime(currentTime);
            insertedUserInfoList.add(userInfo);
        }
        return insertedUserInfoList;
    }
}
