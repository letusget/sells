package com.lll.service;

import com.lll.DTO.OrderDTO;
import com.lll.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单 Service 层
 */
public interface OrderService
{
    /**  创建订单
     * 一个订单下可能有多个订单明细
     * 这里采用 DTO 类来实现
     * */
    //OrderMaster create(OrderMaster orderMaster );
    OrderDTO create(OrderDTO orderDTO);

    /**   查询单个订单  */
    OrderDTO  findById(String orderId);

    /**  查询某个用户的订单列表  */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**  取消订单  */
    OrderDTO cancel(OrderDTO orderDTO);

    /**  完结订单  */
    OrderDTO finish(OrderDTO orderDTO);

    /**  支付订单  */
    OrderDTO paid(OrderDTO orderDTO);

    /**  带分页查询所有的订单列表  */
    Page<OrderDTO> findList(Pageable pageable);

}
