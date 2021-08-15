<html>
    <head>
        <#--
        <meta charset="utf-8">
        <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
        <#assign  path="${springMacroRequestContext.getContextPath()}">
        <script src="${path}/static/js/jquery-3.2.1.js" type="text/javascript" ></script>
        <script src="${path}/static/js/time.js" type="text/javascript" ></script>
        -->
        <!-- 如果以上每个文件夹下都有相同名称的资源文件的时候, Spring Boot获取顺序依次为:
        META-INF/resource下==>resources下==>static下==>public下 -->
        <#-- (1) 引入头文件 -->
        <#include "../common/header.ftl">

    </head>

    <body>
    <#--自己写的 div start-->
    <div id="wrapper" class="toggled">
        <#--2.边栏sidebar-->
        <#include "../common/nav.ftl">

        <div id="page-content-wrapper">
            <#--3. 某个订单的信息的详细列表 start -->
            <div class="container-fluid">
                <div class="row clearfix">

                    <#--某个订单信息 start -->
                    <div class="col-md-8 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>订单号</th>
                                <th>订单总金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>订单创建时间</th>
                                <th>订单修改时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>${orderDTO.getPayStatusEnum().message}</td>
                                <td>${orderDTO.createTime}</td>
                                <td>${orderDTO.updateTime}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <#--某个订单信息 end -->

                    <#--某个订单详情信息 start -->
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>商品名称</th>
                                <th>商品价格</th>
                                <th>商品数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>

                            <tbody>
                                <#if orderDTO.orderDetails??>
                                    <#--此处处理有问题！！-->
                                  <#list orderDTO.orderDetails as orderDetail>
                                        <tr>
                                            <td>${orderDetail_index+1}</td>
                                            <td>${orderDetail.productName}</td>
                                            <td>${orderDetail.productPrice}</td>
                                            <td>${orderDetail.productQuantity}</td>
                                            <td>${orderDetail.productQuantity*orderDetail.productPrice}</td>
                                        </tr>

                                    </#list>
                                </#if>
                            </tbody>
                        </table>
                    </div>
                    <#--某个订单详情信息 end -->

                    <#--订单状态为“新订单” 完结订单 和 取消订单 start -->
                    <div  class="col-md-12 column">
                        <#if orderDTO.getOrderStatusEnum().message=="新订单">
                            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}"  type="button" class="btn btn-default btn-primary">完结订单</a>
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}"  type="button" class="btn btn-default btn-danger">取消订单</a>
                        </#if>
                    </div>
                    <#--订单状态为“新订单” 完结订单 和 取消订单 end -->

                </div>
                <#--某个订单信息 end -->

            </div>
        </div>
    <#--某个订单的信息的详细列表 end -->
    </div>
    <#--自己写的 div end -->
    </body>
</html>