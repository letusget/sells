package com.lll.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lll.DTO.OrderDTO;
import com.lll.entity.OrderDetail;
import com.lll.enums.ResultEnum;
import com.lll.exception.SellException;
import com.lll.form.OrderForm;
import javafx.application.Preloader;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 将OrderForm转换成OrderDTO 方法
 */
@Slf4j
public class OrderForm2OrderDTOConverter
{
    /**
     * 将OrderForm转换成OrderDTO
     */
    public static OrderDTO convert(OrderForm orderForm)
    {
        Gson gson=new Gson();
        List<OrderDetail> orderDetailList=new ArrayList<OrderDetail>();
        //List 对象
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderDTO.getOrderId());

        try
        {
            //将 OrderForm 转换为OrderDTO
            //gson.fromJson(String json, Type typeOfT);
            orderDetailList=gson.fromJson
                    (orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (JsonSyntaxException e)
        {
            log.error("[对象转换]错误,string={}",orderForm.getItems());
            //抛出自定义异常： 参数不正确
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(orderDetailList);

        return orderDTO;
    }



}
