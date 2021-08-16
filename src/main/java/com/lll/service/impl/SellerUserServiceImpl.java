package com.lll.service.impl;

import com.lll.dao.SellerInfoDAO;
import com.lll.entity.SellerInfo;
import com.lll.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 卖家端用户信息Service实现类
 */
@Service
@Transactional
public class SellerUserServiceImpl implements SellerInfoService
{
    /** 卖家信息DAO */
    @Autowired
    private SellerInfoDAO sellerInfoDAO;


    /**
     * 通过openid查询卖家端信息
     *
     * @param openid
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDAO.findByOpenid(openid);
    }
}
