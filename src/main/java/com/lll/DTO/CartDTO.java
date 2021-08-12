package com.lll.DTO;

import lombok.Data;

/**
 * 购物车DTO
 * 购物车数据传输
 */
@Data
public class CartDTO
{
    /**
     * 商品 ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 无参构造
     */
    public CartDTO()
    {

    }

    /**
     * 有参构造
     */
    public CartDTO(String productId, Integer productQuantity)
    {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }



}
