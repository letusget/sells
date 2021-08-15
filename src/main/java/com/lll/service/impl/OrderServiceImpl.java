package com.lll.service.impl;

import com.lll.DTO.CartDTO;
import com.lll.DTO.OrderDTO;
import com.lll.converter.OrderMaster2OrderDTOConverter;
import com.lll.dao.OrderDetailDAO;
import com.lll.dao.OrderMasterDAO;
import com.lll.entity.OrderDetail;
import com.lll.entity.OrderMaster;
import com.lll.entity.ProductInfo;
import com.lll.enums.OrderStatusEnum;
import com.lll.enums.PayStatusEnum;
import com.lll.enums.ResultEnum;
import com.lll.exception.SellException;
import com.lll.service.OrderService;
import com.lll.service.ProductInfoService;
import com.lll.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单Service的实现类
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private ProductInfoService productInfoService; // 商品Serivce

    @Autowired
    private OrderDetailDAO orderDetailDAO;  //商品明细DAO

    @Autowired
    private OrderMasterDAO orderMasterDAO;  //订单主表DAO



    /**  创建订单  */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO)
    {
        //随机生成订单Id
        String orderId=KeyUtil.genUniqueKey8();

        //商品购买总价
        //可以被包装为 new BigDecimal(BigInteger.ZERO)
        BigDecimal orderTotalPrice=new BigDecimal(0);

        // List<CartDTO> cartDTOList = new ArrayList<CartDTO>(); // 购物车列表

        //1. 以商品表为准查询商品 数量价格
        for (OrderDetail orderDetail:orderDTO.getOrderDetails())
        {
            // 得到查询到的商品信息  根据商品表的主键，查询商品信息（数量、价格……）
            ProductInfo productInfo=productInfoService.findById(orderDetail.getProductId());

            if (productInfo==null)
            {
                //使用自定义异常
                //抛出商品不存在异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价 = 不同商品的价值和（单个商品的单价*此商品的数量）
            // 一条订单明细的价格 = 单个商品的价钱 * 一条订单明细买的某个商品的个数
            //2.1 把给订单下的每个订单的明细数据金额
            // 一条订单忙些的价钱=单个商品的价钱 * 一条订单的商品数量

            // 2.2 求该订单下的每个订单明细数据的 金额总和
            //由于 商品价格 的类型是 bigdecimal，有自己的运算符号来表示*：BigDecimal.multiply()
            //而且要均为BigDecimal 类型才可以计算，故要将Integer 类型的数量转换为 BigDecimal

            //表示订单内不用商品的数量
            // orderTotalPrice = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderTotalPrice);
            orderTotalPrice=productInfo.getProductPrice().multiply
                    (new BigDecimal(orderDetail.getProductQuantity())).add(orderTotalPrice);

            //订单明细先入库，然后订单入库
            //入库要自己生成主键，可以使用随机数但是推荐使用UUID
            orderDetail.setDetailId(KeyUtil.genUniqueKey8());
            orderDetail.setOrderId(orderId);
            //将productInfo中的对象复制到orderDetail中
            BeanUtils.copyProperties(productInfo,orderDetail);

            orderDetailDAO.save(orderDetail);

            // CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            // cartDTOList.add(cartDTO);

        }

        // 3. 将订单信息写入数据库(OrderMaster和OrderDetail)
        OrderMaster orderMaster=new OrderMaster();

        //先设置orderId 再copy
        orderDTO.setOrderId(orderId);
        //将orderDTO对象中的内容复制到orderMaster对象
        BeanUtils.copyProperties(orderDTO,orderMaster);

        //orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderTotalPrice);
        orderMaster.setOrderStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDAO.save(orderMaster);

        // 4. 扣库存
        //TODO 不太理解
        List<CartDTO> cartDTOList=
                orderDTO.getOrderDetails().stream().map
                        (e ->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);
        BeanUtils.copyProperties(orderMaster,orderDTO);

        return orderDTO;
    }

    /**  查询单个订单  */
    @Override
    public OrderDTO findById(String orderId)
    {
        //根据订单ID 查询订单信息
        OrderMaster orderMaster=orderMasterDAO.findById(orderId).orElse(null);
        if (orderMaster==null)
        {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //根据订单 ID 查询  订单明细信息列表
        List<OrderDetail> orderDetailList=orderDetailDAO.findByOrderId(orderId);
        //CollectionUtils可以实现对象的 交并差处理
        if (CollectionUtils.isEmpty(orderDetailList))
        {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        //set到返回值orderDTO中去
        orderDTO.setOrderDetails(orderDetailList);

        return orderDTO;

    }

    /**  查询订单列表  */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable)
    {
        //不需要展示订单详情 --> 前端的页面展示订单列表的时候不展示订单详情
        //根据微信端的openid 查询订单主表信息
        Page<OrderMaster> orderMasterPage=orderMasterDAO.queryByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());


        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    /**  取消订单  */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO)
    {
        // 1. 判断订单状态
        //先要确定是否是新订单，如果不是新订单则不能取消，需要给出对应的提示信息，即抛出自定义异常：不是新订单不能取消
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            //不是新订单，抛出自定义异常： 该订单不是新订单不能取消
            log.error("[取消订单] 订单状态不正确，orderId={} orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);

        }

    // 2. 修改订单状态
    orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //修改订单功能
        OrderMaster updateOrderMaster=orderMasterDAO.save(orderMaster);
        if (updateOrderMaster==null)
        {
            log.error("[取消订单] 更新失败,orderMaster={}",orderMaster);
            //抛出自定义异常 “订单更新失败”
            throw  new SellException(ResultEnum.ORDER_UPDATE_FALL);
        }

        // 3. 返回库存
        //根据订单ID 返回订单详情
        List<OrderDetail> orderDetailList=orderDetailDAO.findByOrderId(orderDTO.getOrderId());
        orderDTO.setOrderDetails(orderDetailList);
        //如果订单明细列表为空，则抛出自定义异常： 订单明细为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails()))
        {
            log.error("[取消订单]订单中无商品详情,orderDTO={}",orderDTO);
            //抛出自定义异常： 订单明细为空
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        //返回购物车列表
        List<CartDTO> cartDTOList=orderDTO.getOrderDetails().stream().map
                (e -> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        //增加库存
        productInfoService.increaseStock(cartDTOList);

        // 4. 如果已支付, 需要给用户退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode()))
        {
            //TODO  退款，需要用到微信支付接口
        }

        return orderDTO;
    }

    /**  完结订单  */
    @Override
    public OrderDTO finish(OrderDTO orderDTO)
    {
        //1. 判断订单状态
        //不是新订单,就不能取消
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            log.error("[完结订单] 订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 修改订单状态
        //将订单状态改为完结
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        // 修改订单状态为完结
        OrderMaster updateResult=orderMasterDAO.save(orderMaster);
        if (updateResult == null)
        {
            log.error("[完结订单]更新失败, orderMaster={}", orderMaster);
            // 订单更新失败
            throw new SellException(ResultEnum.ORDER_UPDATE_FALL);
        }
        return orderDTO;

    }

    /**  支付订单  */
    @Override
    public OrderDTO paid(OrderDTO orderDTO)
    {
        //1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            log.error("[订单支付完成] 订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            // 抛出自定义异常: "订单状态不正确"
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 判断支付状态
        //如果不是 等待支付的状态，则抛出自定义异常，订单支付状态不正确
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode()))
        {
            log.error("[订单支付完成]订单支付状态不正确,orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //3.修改支付状态
        //修改订单支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //修改支付状态
        OrderMaster updateResult = orderMasterDAO.save(orderMaster);
        if (updateResult == null)
        {
            log.error("[订单支付完成] 更新失败,orderMaster={}",orderMaster);
            // 抛出自定义异常: "订单更新失败"
            throw new SellException(ResultEnum.ORDER_UPDATE_FALL);

        }

        return orderDTO;
    }

    /**
     * 带分页查询所有的订单列表
     *
     * @param pageable
     */
    @Override
    public Page<OrderDTO> findList(Pageable pageable)
    {
        //带分页 查询所有的 订单列表
        Page<OrderMaster> orderMasterPageList=orderMasterDAO.findAll(pageable);

        //OrderMaster 转换为 OrderDTO
        List<OrderDTO> orderDTOList =OrderMaster2OrderDTOConverter.convert(orderMasterPageList.getContent());

        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPageList.getTotalElements());
    }
}
