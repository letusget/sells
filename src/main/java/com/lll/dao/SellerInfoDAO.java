package com.lll.dao;

import com.lll.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息
 */
public interface SellerInfoDAO extends JpaRepository<SellerInfo, String>
{
    /** 根据openid查询用户信息  */
    SellerInfo findByOpenid(String openid);

}
