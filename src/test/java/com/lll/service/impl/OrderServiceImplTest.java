package com.lll.service.impl;

import com.lll.DTO.OrderDTO;
import com.lll.entity.OrderDetail;
import com.lll.enums.OrderStatusEnum;
import com.lll.service.OrderService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 订单Service 实现类 的测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest
{
    //订单Service
    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID="1101110";
    private final String ORDER_ID="PyKC2siW";

    /**
     * 创建订单
      */
    @Test
    public void create()
    {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("清风");
        orderDTO.setBuyerAddress("成岚");
        orderDTO.setBuyerAddress("南京");
        orderDTO.setBuyerPhone("18658415864");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList=new ArrayList<OrderDetail>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("2");   //需要在库中真实存在
        o1.setProductQuantity(2); //设置商品数量

        OrderDetail o2=new OrderDetail();
        o2.setProductId("3");
        o2.setProductQuantity(1);

        //在订单明细列表中 放入 订单明细信息
        orderDetailList.add(o1);
        orderDetailList.add(o2);

        // 设置商品明细列表
        orderDTO.setOrderDetails(orderDetailList);

        // 创建订单
        OrderDTO result=orderService.create(orderDTO);

        log.info("[创建订单] result={}",result);
        System.out.println(result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findById()
    {
        OrderDTO orderDTO=orderService.findById(ORDER_ID);
        log.info("[查询单个订单] orderDTO={}",orderDTO);

        System.out.println(orderDTO);
        Assert.assertEquals(ORDER_ID,orderDTO.getOrderId());
    }

    @Test
    public void findList()
    {
        //查询第0页（页面中第一页），每页两条数据
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(BUYER_OPENID,request);
        System.out.println(orderDTOPage);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    /**  取消订单  */
    @Test
    public void cancel()
    {
        OrderDTO orderDTO=orderService.findById(ORDER_ID);
        OrderDTO result=orderService.cancel(orderDTO);
        System.out.println(result);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish()
    {

    }

    @Test
    public void paid()
    {

    }
}