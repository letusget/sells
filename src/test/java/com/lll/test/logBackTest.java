package com.lll.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 把 @Slf4j --> logback框架和 Spring AOP 结合起来
 * Created by liyan on 2021/8/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class logBackTest {
  /** 测试logback日志框架 */
  @Test
  public void testLogback() {

    // 测试日志框架 slf4j 下面的 logback的使用
    // logback中有5个日志级别: trace < debug < info < warn < error  由低到高
    // 如果设置的日志级别为 debug  那只有 level >= debug的日志级别会被打印出来
    // log4j中8个级别的log(除去off和all,可以说分为6个级别) [off]、fatal、(error、warn、info、debug、trace)、 [all]从高到低

    String username = "lll";
    String password = "123456";
    log.error("error...");
    log.debug("debut...");
    log.info("info...");
    log.error("username={} , password={}", username, password);

    System.out.println("it is OK ");

    /*
    * try
       {
            // 有可能发生异常的代码  TODO
       } catch (Exception e)
       {
            // e.printStackTrace();
           log.error("error...");    // 产品发布以后
           // log.debug("debug...");  // 开发中...

       }
    * */
  }
}
