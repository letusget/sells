package com.lll.controller;

import com.lll.DTO.OrderDTO;
import com.lll.enums.ResultEnum;
import com.lll.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 分页查询 所有用户订单列表
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController
{
    //订单Service
    @Autowired
    private OrderService orderService;

    /**
     * http://192.168.1.19:8080/seller/order/list
     * 订单列表
     * page 第几页, 从1页开始  必填项(给个默认值 "1")
     * size 一页有多少条数据   必填项(给个默认值 "2")
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "2") Integer size,
                             Map<String,Object> map)
    {
        PageRequest pageRequest=PageRequest.of(page-1,size);

        //带分页查询所有的订单列表
        Page<OrderDTO> orderDTOPageList=orderService.findList(pageRequest);
        //带分页查询到的订单列表
        map.put("orderDTOPageList",orderDTOPageList);
        //设置当前页
        map.put("currentPage",page);
        // 设置每页显示多少条数据
        map.put("size",size);

        //
        return new ModelAndView("order/list",map);

    }

    /**
     * 取消订单
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map, HttpServletRequest request)
    {
        //根据订单Id 查询相关订单信息
        OrderDTO orderDTO=orderService.findById(orderId);
        String contextPath="";
        if (orderDTO==null)
        {
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            //获取应用名，较为灵活
            contextPath=request.getContextPath();
            map.put("url",contextPath+"/seller/order/list");
            return new ModelAndView("common/error",map);

        }
        OrderDTO cancelOrderDTO=orderService.cancel(orderDTO);
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    /**
     * 订单详情
     * http://192.168.1.19:8080/sell/seller/order/detail
     *http://192.168.1.19:8080/sell/seller/order/detail?orderId=1626841653412798517
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")
                                           String orderId,Map<String,Object> map,HttpServletRequest request)
    {
        String contextPath="";
        OrderDTO orderDTO=new OrderDTO();
        try{
            //根据订单ID 查询相关订单信息
            orderDTO=orderService.findById(orderId);
        }catch (Exception e)
        {
            log.error("[买家端查询订单异常]发生异常{}",e);
            //可以灵活取到应用名
            contextPath=request.getContextPath();
            map.put("url",contextPath+"/seller/order/list");
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);

    }



}

