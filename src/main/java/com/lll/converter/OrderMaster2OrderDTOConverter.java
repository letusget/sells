package com.lll.converter;

import com.lll.DTO.OrderDTO;
import com.lll.entity.OrderDetail;
import com.lll.entity.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 新增两个方法：
 * 将OrderMaster 转换成OrderDTO方法
 * 将List<OrderMaster> 转换成 List<OrderDTO>
 * 参考：https://blog.csdn.net/weixin_43469680/article/details/108097008
 */

public class OrderMaster2OrderDTOConverter
{
    /** 将 OrderMaster 转换成 OrderDTO */
    public static OrderDTO convert(OrderMaster orderMaster)
    {
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    /** 将  List<OrderMaster> 转换成  List<OrderDTO>  */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList)
    {
        return orderMasterList.stream().map(e ->convert(e)).collect(Collectors.toList());
    }

}
