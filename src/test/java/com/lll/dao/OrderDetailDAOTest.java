package com.lll.dao;

import com.lll.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 订单明细表DAO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDAOTest {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    /**
     * 新增订单明细测试
     */
    @Test
    public void saveTest()
    {/*
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("1956782162833543926");
        orderDetail.setProductIcon("https://tvax2.sinaimg.cn/large/006x3t5Xgy1gtdrzgf2bvj60sg0sfgtf02.jpg");
        orderDetail.setProductId("4");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(22.5));
        orderDetail.setProductQuantity(3);
*/
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567811");
        orderDetail.setOrderId("1956782162833543926");
        orderDetail.setProductIcon("https://tva1.sinaimg.cn/large/006x3t5Xgy1gtdseqikqnj60zk0np41q02.jpg");
        orderDetail.setProductId("6");
        orderDetail.setProductName("珍珠奶茶");
        orderDetail.setProductPrice(new BigDecimal(18));
        orderDetail.setProductQuantity(3);


        OrderDetail result=orderDetailDAO.save(orderDetail);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /** 根据订单编号 查询相关订单信息 */
    @Test
    public void findByOrderId()
    {
        List<OrderDetail> orderDetailList=orderDetailDAO.findByOrderId("1956782162833543926");
        System.out.println(orderDetailList);
        Assert.assertNotEquals(0,orderDetailList.size());

    }
}