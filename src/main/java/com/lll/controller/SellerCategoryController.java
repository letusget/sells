package com.lll.controller;

import com.lll.entity.ProductCategory;
import com.lll.exception.SellException;
import com.lll.form.CategoryForm;
import com.lll.service.ProductCategoryService;
import com.lll.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目
 * http://iswilliam.natapp1.cc/seller/product/list
 * http://localhost:8080/sell/seller/product/list
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController
{
    //商品类目Service
    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 查询商品类目列表
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map)
    {
        //查询所有商品类目
        List<ProductCategory> categoryList=categoryService.findAll();
        // 设置商品类目列表
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 弹出 商品类目修改页面
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false)
                                          Integer categoryId,Map<String,Object> map)
    {
        if (categoryId!=null)
        {
            ProductCategory productCategory=categoryService.findById(categoryId);
            map.put("category",productCategory);
        }
        return new ModelAndView("category/index",map);
    }

    /**
     * 保存 商品类目
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map)
    {
        if (bindingResult.hasErrors())
        {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try
        {
            if (form.getCategoryId() != null)
            {
                productCategory = categoryService.findById(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            categoryService.save(productCategory);
        } catch (SellException e)
        {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }



}
