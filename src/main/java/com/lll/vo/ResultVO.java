package com.lll.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *  http 请求返回的最外层对象
 */
@Data
/*泛型
VO : Result Object  值对象，最终返回的JSON 数据格式
需要返回 code(Integer)  msg(String)   data(泛型 T)  （固定就是这三种值）

 */
public class ResultVO<T> implements Serializable
{
    /**
     * 序列化接口
     */
    private static final long serialVersionUID=1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private  String msg;

    /**
     * 具体内容
     */
    private T data;

}
