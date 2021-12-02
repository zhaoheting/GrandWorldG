package com.example.GrandWorldG.mapper;

import com.example.GrandWorldG.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper of order info.
 *
 * @author HeTing.Zhao
 * @since 2021/12/2
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * Select order info by user id.
     *
     * @param userId Id of user.
     * @return {@link OrderInfo}
     */
    OrderInfo selectOrderInfoByUserId(Long userId);
}