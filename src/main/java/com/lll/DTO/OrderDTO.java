package com.lll.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * 订单DTO
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert //这里需要这个注解，否则在单元测试时就会报错：Column 'create_time' cannot be null
@Table(name = "order_master")
// 当JSON返回数据中的属性名对应的属性值为null是则最终返回的JSON数据中就不返回
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO
{
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")      //处理时间格式
    private Date updateTime;

    /** 订单明细列表 */
    // 这个字段在表中不存在
    @Transient
    //要求JSON数据中这个字段必须返回  但是返回的又不能是null 为null时显示默认值
    //private List<OrderDetail> orderDetails = new ArrayList<>();
            // 在规范化接口文档中(如果字段为null则统一不返回) orderDetailList是作为非必须返回字段
    List<OrderDetail> orderDetails;

}
