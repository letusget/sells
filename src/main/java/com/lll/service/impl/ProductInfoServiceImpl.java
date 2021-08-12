package com.lll.service.impl;

import com.lll.DTO.CartDTO;
import com.lll.dao.ProductInfoDAO;
import com.lll.entity.ProductInfo;
import com.lll.enums.ProductStatusEnum;
import com.lll.enums.ResultEnum;
import com.lll.exception.SellException;
import com.lll.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品方法的实现类
 */
@Service
//@Transactional  //事物处理
public class ProductInfoServiceImpl implements ProductInfoService
{

    @Autowired
    private ProductInfoDAO productInfoDAO;

    /**
     * 根据商品ID查询商品信息
     * categoryId  商品类目ID
     *
     * */
    @Override
    public ProductInfo findById(String productId)
    {
        return productInfoDAO.findById(productId).orElse(null);
    }

    /** 查询所有上架商品信息列表
     * @return*/
    @Override
    public List<ProductInfo> findUpAll()
    {
        //return (Page<ProductInfo>) productInfoDAO.queryByProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoDAO.queryByProductStatus(ProductStatusEnum.UP.getCode());
        //调用 JPA 提供的方法
    }

    /** 分页查询所有商品信息列表
     * @return*/
    @Override
    public Page<ProductInfo> findAll(Pageable pageable)
    {
        //return (ProductInfo) productInfoDAO.findAll(pageable);
        return productInfoDAO.findAll(pageable);
    }

    /** 新增商品  */
    @Override
    public ProductInfo save(ProductInfo productInfo)
    {
        return productInfoDAO.save(productInfo);
    }

    /**
     * 添加库存
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList)
    {
        for (CartDTO cartDTO:cartDTOList)
        {
            // 根据商品ID查询 商品信息
            ProductInfo productInfo = productInfoDAO.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null)
            {
                // 抛出自定义异常 "该商品不存在"
                throw new SellException(ResultEnum.PRODUCT_NOT_EXSIT);
            }
            // 增加库存
            Integer stocksNum = productInfo.getProductStock() + cartDTO.getProductQuantity();
            // 设置库存
            productInfo.setProductStock(stocksNum);
            // 更新库存
            productInfoDAO.save(productInfo);


        }
    }

    /**
     * 减 库存
     * @param cartDTOList
     */
    @Override
    @Transactional //事物处理,参考：https://www.jianshu.com/p/befc2d73e487
    public void decreaseStock(List<CartDTO> cartDTOList)
    {
        for (CartDTO cartDTO:cartDTOList)
        {
            //根据商品Id 查询商品信息
            ProductInfo productInfo=productInfoDAO.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo==null)
            {
                //抛出自定义异常，显示 该商品不存在
                throw new SellException(ResultEnum.PRODUCT_NOT_EXSIT);
            }

            //剩余库存
            //TODO 用Redis 的锁机制来解决 -- 项目优化
            Integer remainStock = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if (remainStock<0)
            {
                //抛出自定义异常，显示 商品库存不正确
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            //重新设置库存
            productInfo.setProductStock(remainStock);

            //保存商品信息
            productInfoDAO.save(productInfo);


        }

    }
}
