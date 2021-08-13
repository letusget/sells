package com.lll.exception;

import com.lll.enums.ResultEnum;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class SellException extends RuntimeException
{
    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 返回异常消息
     * @param resultEnum
     */
    public SellException(ResultEnum resultEnum)
    {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message)
    {
        super(message);
        this.code = code;
    }



}
