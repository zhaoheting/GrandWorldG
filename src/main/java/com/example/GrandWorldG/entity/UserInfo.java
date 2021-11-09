package com.example.GrandWorldG.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * User info entity.
 *
 * @author HeTing.Zhao
 * @since 2021/10/17
 **/
@Getter
@Setter
public class UserInfo {

    private Long userId;

    private String username;

    private String password;

    private Timestamp createTime;

    private Timestamp lastUpdateTime;

    private String domainUsername;

    private String socialSecurityNumber;
}
