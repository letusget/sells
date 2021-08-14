<html>
<head>
    <meta charset="UTF-8">
    <title>成功提示</title>
    <link>

    <script></script>
    <script></script>

</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <#--<div class="alert alert-dismissable alert-danger">-->
                <div class="alert alert-dismissable alert-success">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4>
                        取消订单成功!
                    </h4>
                    即将跳转页面:
                    <a href="${url}" class="alert-link" style='text-decoration:none;'>3</a><strong><span>s 后自动跳转</span></strong>
                    <input type="hidden" value="${url}" id="hiddenUrl">
                </div>
            </div>
        </div>
    </div>

</body>
</html>