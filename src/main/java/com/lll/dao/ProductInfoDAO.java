package com.lll.dao;

import com.lll.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
商品DAO
 */
public interface ProductInfoDAO extends JpaRepository<ProductInfo,String>
{
    /**
     * 通过商品状态查询上架的商品
     */
    List<ProductInfo> queryByProductStatus(Integer productStatus);
    //Page<ProductInfo> queryByProductStatus(Integer productStatus);
}
