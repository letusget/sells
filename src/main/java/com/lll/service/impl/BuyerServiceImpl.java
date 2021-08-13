package com.lll.service.impl;

import com.lll.DTO.OrderDTO;
import com.lll.enums.ResultEnum;
import com.lll.exception.SellException;
import com.lll.service.BuyerService;
import com.lll.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
买家Service 实现类
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService
{
    @Autowired
    private OrderService orderService;

    /**
     * 查询一个订单
     *
     * @param openid
     * @param orderId
     */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId)
    {
        //根据订单ID 查询单个订单
        OrderDTO orderDTO= orderService.findById(orderId);
        if (orderDTO==null)
        {
            return null;
        }

        //判断是否是属于自己的订单，抛出自定义异常： 该订单不属于当前用户
        if (!orderDTO.getOrderId().equalsIgnoreCase(openid))
        {
            log.error("[查询订单]订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);

        }
        return orderDTO;

    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId)
    {
        //根据订单ID 查询单个订单
        OrderDTO orderDTO=orderService.findById(orderId);
        if (orderDTO==null)
        {
            log.error("[取消订单] 查不到订单,orderId={}",orderId);
            return null;
        }
        return orderService.cancel(orderDTO);

    }
}
