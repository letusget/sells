package com.lll.service.impl;

import com.lll.dao.ProductInfoDAO;
import com.lll.entity.ProductInfo;
import com.lll.enums.ProductStatusEnum;
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
public class ProductInfoServiceImpl implements ProductInfoService {
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
}
