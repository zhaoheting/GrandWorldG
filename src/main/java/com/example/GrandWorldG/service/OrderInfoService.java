package com.example.GrandWorldG.service;

import com.example.GrandWorldG.entity.OrderInfo;

/**
 * Order info interface.
 *
 * @author HeTing.Zhao
 * @since 2021/12/2
 **/
public interface OrderInfoService {
    OrderInfo getOrderInfoByUserId(Long orderId);
}