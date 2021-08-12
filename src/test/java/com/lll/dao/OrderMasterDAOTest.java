package com.lll.dao;

import com.lll.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest
{
    @Autowired
    private OrderMasterDAO orderMasterDAO;  //订单主表 DAO

    private final String OPENID="1314520";

    /**
     * 订单主表保存的测试方法
     */
    @Test
    public void saveTest()
    {
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("1956782162833543926");
        orderMaster.setBuyerName("夏海藻");
        orderMaster.setBuyerPhone("13151318786");
        orderMaster.setBuyerAddress("淮安市清江浦区枚皋路1号");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(22.5));

        OrderMaster saveOrderMaster=orderMasterDAO.save(orderMaster);
        System.out.println(saveOrderMaster);
        //Assert.assertNotNull(saveOrderMaster);
    }

    /**  根据微信端的openid查询订单主表信息  */
    @Test
    public void queryByBuyerOpenid() throws Exception
    {
        PageRequest pageRequest=PageRequest.of(0,2);    //第0（1） 页，每页2条数据
        Page<OrderMaster> orderMasterResult=orderMasterDAO.queryByBuyerOpenid(OPENID,pageRequest);
        Assert.assertNotEquals(0,orderMasterResult.getTotalElements());
        System.out.println(orderMasterResult.getTotalElements());
        //System.out.println(orderMasterResult);


    }
}