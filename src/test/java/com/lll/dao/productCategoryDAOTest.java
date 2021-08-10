package com.lll.dao;

import com.lll.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class productCategoryDAOTest {
//注解实现类
    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    /**
     * 根据商品类目ID 查询商品类目信息
     */
    @Test
    public void findOndTest()
    {
        //根据主键查询对应的javaBean 信息， orelse中返回的是一个默认值，可以使用null，也可以使用自定义的值
        ProductCategory productCategory=productCategoryDAO.findById(1).orElse(null);
        System.out.println(productCategory.toString());
    }

    /**
     *  新增商品类目
     *  Table '项目名称..hibernate_sequence' doesn't exist的解决方法:
     *  https://blog.csdn.net/Interesting_Talent/article/details/81454104
     */
    @Test
    public void saveTest()
    {
     //使用 save 方法，既可以修改 也可以新增，修改一般是根据主键进行查询修改
        //新增（没有主键）--> 进入新增页面（一般为空白页面）--> 用户填入要填写的数据 --> 提交数据
        //修改（有主键）  --> 查询要修改的数据信息页面  -->   修改数据  --> 提交数据
        ProductCategory productCategory = new  ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(10);
        //productCategory.setCategoryType(3);
        //productCategoryDAO.save(productCategory);

        ProductCategory queryProductCategory  =  productCategoryDAO.save(productCategory);
        // Assert.assertNotNull(queryProductCategory);
        //断言 assertNotEquals 的参数分别为：不被期望的值 和 实际的值的比较
        Assert.assertNotEquals(null, queryProductCategory);  // 这里的2个方法同价

    }

    /**
     *  修改商品类目
     *  要更新主键categoryId
     */
    @Test
    @Transactional  //测试已经修改入库的数据回滚
    public void saveTest2()
    {
        ProductCategory productCategory = new  ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(4);
        productCategoryDAO.save(productCategory);
    }

    @Test
    public void saveTest3()
    {
        // ProductCategory productCategory = new  ProductCategory();
        ProductCategory productCategory = productCategoryDAO.findById(2).orElse(null);
        productCategory.setCategoryType(6);
        productCategoryDAO.save(productCategory);
    }

    /**
     * 一次性 根据商品类目查询 测试
     */
    @Test
    public void queryCategoryTypeInTest()
    {
        List<Integer> list = Arrays.asList(1,2);

        List<ProductCategory> result = productCategoryDAO.queryByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }




}