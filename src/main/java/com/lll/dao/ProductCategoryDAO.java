package com.lll.dao;

import com.lll.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目DAO
 */
//JpaRepository<> 对应的参数 分别是对应的JavaBean 和 主键类型
public interface ProductCategoryDAO extends JpaRepository<ProductCategory,Integer>{
    /** 一次性 根据商品类目查询 */
    List<ProductCategory> queryByCategoryTypeIn(List<Integer> catagroyTypeList);

}
