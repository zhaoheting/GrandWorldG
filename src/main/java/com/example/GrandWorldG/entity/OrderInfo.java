package com.example.GrandWorldG.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * TODO
 *
 * @author HeTing.Zhao
 * @since 2021/12/2
 **/
@Getter
@Setter
public class OrderInfo {
    private Long orderId;
    private int OrderPrice;
    private Timestamp createTime;
    private Long userId;
}
