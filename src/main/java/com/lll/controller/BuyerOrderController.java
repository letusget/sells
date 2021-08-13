package com.lll.controller;

import com.lll.DTO.OrderDTO;
import com.lll.converter.OrderForm2OrderDTOConverter;
import com.lll.enums.ResultEnum;
import com.lll.exception.SellException;
import com.lll.form.OrderForm;
import com.lll.service.BuyerService;
import com.lll.service.OrderService;
import com.lll.service.impl.BuyerServiceImpl;
import com.lll.utils.ResultVOUtil;
import com.lll.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户订单
 * http://localhost:8080/buyer/order/create
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController
{
    @Autowired
    private OrderService orderService;

    //1. 创建订单 /buyer/order/create
    //实体类校验信息BingingResult返回结果绑定
    //http://localhost:8080/buyer/order/create
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult)
    {
        //  实体类校验信息返回结果 错误处理
        if (bindingResult.hasErrors())
        {
            log.error("[创建订单]参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());

        }

        // 将OrderForm转换成OrderDTO
        OrderDTO orderDTO= OrderForm2OrderDTOConverter.convert(orderForm);

        //判断订单详情列表是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails()))
        {
            log.error("[创建订单]购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        //创建订单
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map=new HashMap<String,String>();
        //设置orderId
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //2. 订单列表 -> GET  /buyer/order/list
    //http://localhost:8080/buyer/order/list
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list
    (@RequestParam("openid") String openid,@RequestParam("page") Integer page,@RequestParam("size") Integer size)
    {
        if (!StringUtils.hasText(openid))
        {
            log.error("[查询订单列表]openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest=PageRequest.of(page,size);
        //查询订单列表
        Page<OrderDTO> orderDTOPage=orderService.findList(openid,pageRequest);

        return ResultVOUtil.success(orderDTOPage.getContent());
        //假如返回的整个data是null 则在JSON数据中不返回
        //return ResultVOUtil.success();

        //这里只设置ResultVo的code的值
        //ResultVO resultVO=new ResultVO();
        //resultVO.setCode(0);
        //return resultVO;

    }

    //3. 订单详情 ->GET   /buyer/order/list
    //http://localhost:8080/buyer/order/detail
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderid)
    {

        OrderDTO orderDTO=orderService.findById(orderid);
        //BuyerServiceImpl buyerServiceImpl=new BuyerServiceImpl();
        //OrderDTO orderDTO= buyerServiceImpl.findOrderOne(openid,orderid);
        return ResultVOUtil.success(orderDTO);
    }

    //4. 取消订单 ->  GET   /buyer/order/cancel
    // http://localhost:8080/buyer/order/cancel
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId)
    {
        //BuyerServiceImpl buyerServiceImpl=new BuyerServiceImpl();
        //OrderDTO orderDTO=buyerServiceImpl.cancelOrder(openid,orderId);
        OrderDTO orderDTO=orderService.findById(orderId);
        //取消订单
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }


}
