package com.lll.enums;

import lombok.Getter;

/**
 * 订单状态
 */
@Getter
public enum OrderStatusEnum
{
    NEW(0,"新订单"),FINISHED(1,"完结"),CANCEL(2,"已取消");

    private Integer code;

    private String message;

    /**
     * 无参构造
     */
    OrderStatusEnum()
    {

    }

    /**
     * 有参构造
     * @param code
     * @param message
     */
    OrderStatusEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }


}
