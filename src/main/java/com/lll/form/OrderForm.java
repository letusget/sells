package com.lll.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单列表
 * 创建订单 public ResultVO<Map<String, String>>  create (@Valid OrderForm orderForm, BindingResult bindingResult)
 * 方法所需要的第一个参数封装
 * 需要导入的包：在pom.xml中导入hibernate-validation的依赖包
 */
@Data
public class OrderForm
{
    /**
     * 买家姓名
     */
    @NotEmpty(message="姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家微信 openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;




}
