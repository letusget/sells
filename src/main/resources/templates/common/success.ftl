<html>
<head>
    <meta charset="UTF-8">
    <title>成功提示</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <#--
    <script src="../../static/js/jquery-3.2.1.js" type="text/javascript" ></script>
    <script src="../../static/js/time.js" type="text/javascript" ></script>
-->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.2.1/jquery.js" ype="text/javascript"></script>

    <script ype="text/javascript">
        $(function()
        {
            // 使用匿名函数方法
            // 1秒调用一次
            setInterval(function()
            {
                // 获取到id为time标签中的内容，现进行判断
                if ($(".alert-link").text() == 1)
                {
                    // 等于0 时清除计时
                    location.href = $('#hiddenUrl').val();
                }else
                {
                    $(".alert-link").text($(".alert-link").text() - 1);
                }
            },1000);
        });
    </script>


</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <#--<div class="alert alert-dismissable alert-danger">-->
                <div class="alert alert-dismissable alert-success">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4>
                        操作订单成功!  <strong>${msg!""}</strong>
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