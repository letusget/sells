package com.lll.service;

import com.lll.entity.ProductCategory;

import java.util.List;

/**
 * 商品类目 Servlet
 */
public interface ProductCategoryService
{
    /**  根据某个商品类目ID查询商品类目 */
    ProductCategory findById(Integer categoryId);

    /** 查询所有的商品类目  */
    List<ProductCategory> findAll();

    /**  根据商品类目ID列表 查询商品类目列表  */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**  新增商品类目  */
    ProductCategory save(ProductCategory  productCategory);

}
