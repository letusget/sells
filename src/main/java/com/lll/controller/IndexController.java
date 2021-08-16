package com.lll.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码实现微信的验证文件放在根目录下
 * https://www.cnblogs.com/pxblog/p/13445128.html
 */
@RestController
public class IndexController
{
    @RequestMapping("MP_verify_rKLU5DbXF062Q0L0.txt")
    public String wxPrivateKey(){
        //直接返回你下载的授权文件里的内容就好
        return "rKLU5DbXF062Q0L0";
    }
}
