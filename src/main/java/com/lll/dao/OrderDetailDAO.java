package com.lll.dao;

import com.lll.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单明细表 DAO
 */
public interface OrderDetailDAO extends JpaRepository<OrderDetail,String>
{
    /**
     * 根据订单编号，查询相关订单信息
     */
    List<OrderDetail> findByOrderId(String orderId);


}
