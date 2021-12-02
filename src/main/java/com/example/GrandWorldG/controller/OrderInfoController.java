package com.example.GrandWorldG.controller;

import com.example.GrandWorldG.entity.OrderInfo;
import com.example.GrandWorldG.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Order info controller.
 *
 * @author HeTing.Zhao
 * @since 2021/12/2
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("/{orderId}")
    public OrderInfo getOrderInfo(@PathVariable("orderId") Long orderId) {
        return orderInfoService.getOrderInfoByUserId(orderId);
    }
}
