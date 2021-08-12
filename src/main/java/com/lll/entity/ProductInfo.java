package com.lll.entity;

import com.lll.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品表 对应的 JavaBean product_info
 */
@Entity
@Data
@DynamicUpdate
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


}
