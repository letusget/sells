<head>
    <meta charset="utf-8">
    <title>卖家后端管理系统</title>
    <#assign  path="${springMacroRequestContext.getContextPath()}">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <#--<link rel="stylesheet" type="text/css" href="${path}/static/css/style.css">-->
    <#--这里要访问静态资源CSS 一定要注意，路径一定要正确，参考：https://blog.csdn.net/weixin_42336011/article/details/104533467
    https://www.jianshu.com/p/cbe6465b62ec -->
    <link rel="stylesheet" type="text/css" href="${path}/css/style.css">

    <script>
        var t = null;
        t = setTimeout(time, 1000); //開始运行
        function time() {
            clearTimeout(t); //清除定时器
            dt = new Date();
            var y = dt.getFullYear();
            var mt = dt.getMonth() + 1;
            var day = dt.getDate();
            var h = dt.getHours(); //获取时
            var m = dt.getMinutes(); //获取分
            var s = dt.getSeconds(); //获取秒
            document.querySelector(".showTime").innerHTML =
                "当前时间：" +
                y +
                "年" +
                mt +
                "月" +
                day +
                "-" +
                h +
                "时" +
                m +
                "分" +
                s +
                "秒";
            t = setTimeout(time, 1000); //设定定时器，循环运行
        }
    </script>

</head>