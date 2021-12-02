package com.example.GrandWorldG.service.impl;

import com.example.GrandWorldG.entity.OrderInfo;
import com.example.GrandWorldG.mapper.OrderInfoMapper;
import com.example.GrandWorldG.service.OrderInfoService;
import org.springframework.stereotype.Service;

/**
 * Order info service.
 *
 * @author HeTing.Zhao
 * @since 2021/12/2
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private final OrderInfoMapper orderInfoMapper;

    public OrderInfoServiceImpl(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    @Override
    public OrderInfo getOrderInfoByUserId(Long orderId) {
        return orderInfoMapper.selectOrderInfoByUserId(orderId);
    }
}
