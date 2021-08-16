package com.lll.controller;

import com.lll.entity.ProductInfo;
import com.lll.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductInfoController
{
    /**
     * 商品Service
     */
    @Autowired
    private ProductInfoService productInfoService;


    /**
     * 买家端 -- 商品列表
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map)
    {
        PageRequest request=PageRequest.of(page-1,size);

        //分页查询商品列表
        Page<ProductInfo> productInfoPageList = productInfoService.findAll(request);
        // 设置商品分页列表
        map.put("productInfoPageList",productInfoPageList);
        // 设置当前页
        map.put("currentPage", page);
        // 设置每页显示多少条数据
        map.put("pageSize", size);
        return new ModelAndView("product/list", map);
    }


}
