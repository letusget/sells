package com.lll.DTO;

import com.lll.entity.OrderDetail;
import com.lll.enums.OrderStatusEnum;
import com.lll.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@DynamicInsert //这里需要这个注解，否则在单元测试时就会报错：Column 'create_time' cannot be null
@Table(name = "order_master")
public class OrderDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    @Id
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信Openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单  */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付  */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 订单明细列表 */
    // 这个字段在表中不存在
    @Transient
    private List<OrderDetail> orderDetails;

}
