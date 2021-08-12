package com.lll.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 对错误结果类的枚举封装
 * 返回给前端提示的消息
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum
{
    PRODUCT_NOT_EXSIT(100,"该商品不存在"),
    PRODUCT_STOCK_ERROR(101,"商品库存不正确（不足）"),
    ORDER_NOT_EXIST(102,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(103,"订单明细不存在"),
    ORDER_STATUS_ERROR(104,"不是新订单，抛出自定义异常： 该订单不是新订单不能取消"),
    ORDER_UPDATE_FALL(105,"订单更新失败"),
    ORDER_DETALL_EMPTY(106,"订单明细为空"),
    ORDER_UPDATE_FAIL(107,"更新失败，请确认订单信息");

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;


}
