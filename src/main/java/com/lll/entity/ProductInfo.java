package com.lll.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lll.enums.ProductStatusEnum;
import com.lll.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表 对应的 JavaBean product_info
 * http://192.168.1.19:8080/sell/seller/product/list
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "product_info")
public class ProductInfo implements Serializable
{
    private static final long serialVersionUID=1L;

    /**
     * 商品ID没有主键自增，由于商品太多，不需要自增
     */
    @Id     //主键
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品缩略图
     */
    private String productIcon;

    /**
     * 商品状态  0表示正常，1 表示下架  -->
     * 魔鬼数字，一定要在接口中进行定义，使用接口常量  或是使用枚举
     * 使用enums 包下的ProductStatusEnum 类来进行定义
     */
    private Integer productStatus= ProductStatusEnum.UP.getCode();

    /**
     * 商品类目编号
     */
    private Integer categoryType;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createTime;

    /** 修改时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updateTime;

    /** 获取产品的枚举类: 商品的各个状态都在里面 */
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum()
    {
        //这里卡了好久，没有发现问题所在，原来是ProductStatusEnum 没有implements CodeEnum
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }


}
