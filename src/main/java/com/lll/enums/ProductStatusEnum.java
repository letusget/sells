package com.lll.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品状态的构造类
 */
@Getter     //设置get 方法，可以得到商品信息
//@Setter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架");
    private Integer code;

    private String message;

    //无参构造器
    ProductStatusEnum()
    {

    }

    //有参构造器
    ProductStatusEnum(Integer code,String message)
    {
        this.code=code;
        this.message=message;
    }

}
