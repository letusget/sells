package com.lll.form;

import lombok.Data;

/**
 * 封装商品类目 修改、商品新增 传递的数据
 */
@Data
public class CategoryForm
{
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

}
