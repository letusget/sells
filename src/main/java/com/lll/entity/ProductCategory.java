package com.lll.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目表对应的 JavaBean，对应 product_category
 */
@Entity
@Data   //使用 lombok自动生成get set 及构造方法
@Table(name = "product_category")
@DynamicUpdate  //可以实现数据库中字段更新时间的记录
public class ProductCategory implements Serializable
{
    /**
     * javaBean 中的字段一定要与数据库中的字段保持一致
     */
    private static final long serialVersionUID = 1L;

    /** 类目id */
    @Id  //主键
    @GeneratedValue //主键自增
    @Column(name = "category_id")
    private Integer categoryId;

    /** 类目名 */
    @Column(name = "category_name")
    private String categoryName;

    /** 类目编号 */
    @Column(name = "category_type")
    private Integer categoryType;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
     private Date createTime;


    /**
     * 提交时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
     private Date updateTime;



}

