package com.lll.service.impl;

import com.lll.dao.ProductCategoryDAO;
import com.lll.entity.ProductCategory;
import com.lll.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目Service实现类
 */

@Service
//@Transactional  //事物处理
public class ProductCategoryServiceImpl implements ProductCategoryService
{
    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    /**
     * 根据某个商品类目ID查询商品类目
     *
     */
    @Override
    public ProductCategory findById(Integer categoryId) {
        return productCategoryDAO.findById(categoryId).orElse(null);
    }

    /**
     * 查询所有商品类目
     * @return
     */
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDAO.findAll();
    }

    /**  根据商品类目ID列表 查询商品类目列表  */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDAO.queryByCategoryTypeIn(categoryTypeList);
    }

    /**  新增商品类目  */
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDAO.save(productCategory);
    }
}
