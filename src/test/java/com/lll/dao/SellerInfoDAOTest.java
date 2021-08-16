package com.lll.dao;

import com.lll.entity.SellerInfo;
import com.lll.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SellerInfoDAOTest
{
    @Autowired
    private SellerInfoDAO sellerInfoDAO; // 用户信息DAO
    /** 添加用户信息  */
    @Test
    public void save()
    {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("lllxxxopenid");

        SellerInfo result = sellerInfoDAO.save(sellerInfo);
        Assert.assertNotNull(result);
    }


    /** 根据openid查询用户信息  */
    @Test
    public void findByOpenid() throws Exception
    {
        SellerInfo sellerInfo = sellerInfoDAO.findByOpenid("lllxxxopenid");
        Assert.assertEquals("lllxxxopenid", sellerInfo.getOpenid());
    }

}