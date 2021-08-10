package com.lll.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/hello/sayHello
 */
@RestController // 返回JSON数据
@RequestMapping("/hello")
public class HelloController {
    /** 查询商品列表方法 */
    @GetMapping("/sayHello")
    public String sayHello()
    {
        return "111111111111111";
    }
}
