package com.lll.controller;

import com.lll.DTO.OrderDTO;
import com.lll.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 分页查询 所有用户订单列表
 */
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
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map)
    {
        PageRequest pageRequest=PageRequest.of(page-1,size);

        //带分页查询所有的订单列表
        Page<OrderDTO> orderDTOPageList=orderService.findList(pageRequest);
        //带分页查询到的订单列表
        map.put("orderDTOPageList",orderDTOPageList);

        return new ModelAndView("order/list",map);

    }


}

