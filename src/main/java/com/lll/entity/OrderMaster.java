package com.lll.entity;

import com.lll.enums.OrderStatusEnum;
import com.lll.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据库 order_master  对应的Javabean
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert //这里需要这个注解，否则在单元测试时就会报错：Column 'create_time' cannot be null
@Table(name = "order_master")
public class OrderMaster implements Serializable
{
    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @Id
    //@Column(name = "order_id")
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信Openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间 */
    //@Column(name = "create_time")
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;


}
