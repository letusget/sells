package com.lll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class TestRest {
    @GetMapping("/test")
    /*public String test(@RequestParam String name,
                       @RequestParam String sex)*/
    public String test()
    {
        //return "姓名："+name+",性别："+sex;
        int sum=0;
        for (int i=0;i<10;i++)
        {
            System.out.println(i);
            sum+=i;
        }
        return sum+"";

    }

    @GetMapping("/test2")
    public String test2()
    {

            System.out.println("================");

        return "这是一只小花猪";

    }

}
