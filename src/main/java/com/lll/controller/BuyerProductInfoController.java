package com.lll.controller;

import com.lll.entity.ProductCategory;
import com.lll.entity.ProductInfo;
import com.lll.service.ProductCategoryService;
import com.lll.service.ProductInfoService;
import com.lll.utils.ResultVOUtil;
import com.lll.vo.ProductInfoVO;
import com.lll.vo.ProductVO;
import com.lll.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * http://localhost:8080/sell/buyer/product/list
 *
 * 配置好后的网址：http://sells.com/#/
 */
@RestController //返回JSON 数据
@RequestMapping("/buyer/product")
public class BuyerProductInfoController
{
    /**
     * 商品 Service
     */
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品类目
     */
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 买家端数据拼接
     *
     * 查询商品类目信息 方法
     * @return
     */
    @GetMapping("/list")
    public ResultVO List()
    {//返回类型为ResultVO

        //1. 查询所有上架商品
        List<ProductInfo> productInfoList=productInfoService.findUpAll();

        //2. 查询商品类目列表  一次性查询
        List<Integer> categoryTypeList=new ArrayList<Integer>();
        for (ProductInfo productInfo:productInfoList)
        {
            /*
            从 查询到所有的上架商品中取出所有的 商品类目编号
            productInfo.getCategoryType()  表示到 查询商品类目列表中
             */
            categoryTypeList.add(productInfo.getCategoryType());
        }

        //查询商品类目列表
        /**
        查询不可以放到 for 循环中去
         */
        List<ProductCategory> productCategoryList=productCategoryService.findByCategoryTypeIn(categoryTypeList);
        /**
         * 将 查询商品类目列表 categoryTypeList 改用 Lambda 表达式  -->  不成功！！！
         */
        //List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        //3. 拼接数据
        //创建一个空的商品列表
        List<ProductVO> productVOList=new ArrayList<>();

        //遍历商品类目列表
        /**
         * 查询不要放在for循环中
         */
        for (ProductCategory productCategory:productCategoryList)
        {
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());   //商品类目名
            productVO.setCategoryType(productCategory.getCategoryType());  //商品类目type

            //创建一个空的商品列表详情
            List<ProductInfoVO> productInfoVOList=new ArrayList<ProductInfoVO>();

            //遍历商品列表
            for (ProductInfo productInfo2:productInfoList)
            {
                //判断商品类目是否相等
                if (productInfo2.getCategoryType().equals(productCategory.getCategoryType()))
                {
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    /*
                      productInfoVO.setProductId(productInfo2.getProductId());
                      productInfoVO.setProductName(productInfo2.getProductName());
                      productInfoVO.setProductPrice(productInfo2.getProductPrice());
                      productInfoVO.setProductDescription(productInfo2.getProductDescription());
                      productInfoVO.setProductIcon(productInfo2.getProductIcon());
                      */
                    /**
                     * 可以将这几个set 方法合并为 BeanUtils.copyProperties(src, dest);
                     */
                    BeanUtils.copyProperties(productInfo2,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                    // 将商品详情对象 放置到 商品详情列表中

                }
            }// end of 内层for循环

            productVO.setProductInfoVOList(productInfoVOList); // 设置商品详情列表  设置foods
            productVOList.add(productVO);

        }// end of 外层for循环


        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        // resultVO.setData(productVO);
        // 将ProductVO设置到data中 ---  ProductVO是商品对象(包含商品类目)
        resultVO.setData(productVOList);
         return resultVO;

        /**
         * 使用工具类来实现
         */
        //return ResultVOUtil.success();

    }


}
