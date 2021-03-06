<#--
<h1>${orderDTOPageList.getTotalPages()}</h1>
<h1>${orderDTOPageList.totalPages}</h1>
<#list orderDTOPageList.content as orderDTO>
    ${orderDTO.orderId} <br/>
</#list>
-->


<html>
<#-- 1. 引入头文件-->

<#--

    <head>
        &lt;#&ndash;
        &lt;#&ndash;<meta charset="utf-8">
        <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">&ndash;&gt;
        &ndash;&gt;
        <#include  "../common/header.ftl">
    </head>
-->

<#include  "../common/header.ftl">
    <body>

    <#--2. 自己写的 div start -->
    <div id="wrapper" class="toggled">
    <#-- 3. 边栏 sidebar-->
        <#include "../common/nav.ftl">

    <#--4. 主要内容 start-->
    <div class="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <#--显示系统时间-->
                <div class="showTime">当前时间：2021年8月17-12时35分16秒</div>
                <#--表格 内容start-->
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>订单号</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                        </thead>

                        <tbody>
                        <#list orderDTOPageList.content as orderDTO>
                           <tr>
                               <td>${orderDTO_index+1}</td>
                               <td>${orderDTO.orderId}</td>
                               <td>${orderDTO.buyerName}</td>
                               <td>${orderDTO.buyerPhone}</td>
                               <td>${orderDTO.buyerAddress}</td>
                               <td>${orderDTO.orderAmount}</td>
                               <td>${orderDTO.getOrderStatusEnum().message}</td>
                               <td>${orderDTO.getPayStatusEnum().message}</td>
                               <td>${orderDTO.createTime}</td>
                               <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>

                               <td>
                                    <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                   <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                    </#if>
                               </td>
                           </tr>
                           </#list>
                        </tbody>
                        <#--主要内容 end-->
                    </table>
                </div>
                <#--表格内容 end-->

                <#--分页 start-->
                <div class="col-md-12 column">
                <#--分页居右-->
                    <ul class="pagination pull-right">

                        <#--上一页处理 start-->
                        <#if currentPage lte 1>
                            <li class="disabled">
                                <a href="#">上一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage -1}&size=${size}">上一页</a>
                            </li>
                        </#if>
                        <#--上一页处理 end-->

                        <#--循环遍历从 DB中查询出来的 带分页查询的订单列表  start-->
                        <#list 1..orderDTOPageList.getTotalPages() as index>
                            <#--将当前页 置灰-->
                            <#if currentPage == index>
                                <li class="disable">
                                    <a href="/sell//seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#--循环遍历从 DB中查询出来的 带分页查询的订单列表  end-->

                        <#--下一页处理 start-->
                        <#if currentPage gte orderDTOPageList.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a>
                            </li>
                        </#if>
                        <#--下一页处理 end-->
                    </ul>
                </div>
                <#--分页 end-->
                </div>
            </div>
        </div>
    </div>
    <#--自己写的 div  end -->
    </body>
</html>
