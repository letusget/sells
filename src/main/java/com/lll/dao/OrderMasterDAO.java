package com.lll.dao;

import com.lll.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单主表
 */
public interface OrderMasterDAO extends JpaRepository<OrderMaster,String>
{
    /**
     * 根据微信端的openid 查询订单主表信息
     */
    Page<OrderMaster> queryByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
