package com.lll.dao;

import com.lll.entity.ProductInfo;
import com.lll.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
商品DAO 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDAOTest {
    @Autowired
    private ProductInfoDAO productInfoDAO;

    /**
     * 新增商品
     * @throws Exception
     */

    @Test
    public void saveTest() throws Exception
    {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("双皮奶");
        productInfo.setProductPrice(new BigDecimal(5.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("白白净净，香醇可口，风味绝佳");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(2);

        ProductInfo productInfoSave=productInfoDAO.save(productInfo);
        System.out.println(productInfoSave);
        //Assert.assertNotNull(productInfoSave);


    }


    /** 通过商品的状态来查上架的商品 */
    @Test
    public void queryByProductStatus() throws Exception
    {
        List<ProductInfo> productInfoList=productInfoDAO.queryByProductStatus(0);
        System.out.println(productInfoList.size());
        System.out.println(productInfoList);
        //Assert.assertNotEquals(0,productInfoList.size());

    }
}