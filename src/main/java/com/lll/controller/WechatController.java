package com.lll.controller;

import com.lll.enums.ResultEnum;
import com.lll.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 微信网页授权
 * http://iswilliam.natapp1.cc/sell/wechat/authorize
 */

//@RestController   // 返回JSON 即使重定向 页面也不会发生跳转
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController
{
    /**
     * 微信公众号Service
     */
    @Autowired
    private WxMpService wxMpService;

    /**
     * 公众号授权
     */
    @GetMapping("/authorize")
    //@RequestMapping("authorize")
    public String  authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //WxMpService wxMpService = new WxMpServiceImpl();

        //  1. 配置
        //application.yml中的mpAppId和mpAppSecret

        //  2. 调用 网页授权方法
        String url = "http://iswilliam.natapp1.cc/sell/wechat/userInfo";
        // 注意returnUrl要Encoder
        // String oauth2buildAuthorizationUrl(String var1, String var2, String var3);
           /* String redirectUrl = wxMpService.oauth2buildAuthorizationUrl
                    (url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
            */

        /**
         * 第一个参数是获得授权码code后回调的地址
         * 第二个是策略：获得简单的授权，还是希望获得用户的信息
         * 第三个参数是我们希望携带的参数:查看API文档需要返回returnUrl 所以我们就携带它
         */
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl
                (url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl,"utf-8"));

        log.info("[微信网页授权]获取code，result={}",redirectUrl);
            return "redirect:" + redirectUrl;

    }

    /**
     * 获取公众号的openid信息
     */
    @GetMapping("userInfo")
    //@RequestMapping("userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) throws UnsupportedEncodingException
    {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e)
        {
            log.error("[微信网页授权]{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId=wxMpOAuth2AccessToken.getOpenId();
        // 这里要特别注意,url里的参数必须写openid,不能写成openId,因为前端代码是获取openid的值
        return "redirect:" + returnUrl + "?openid=" + openId;
        //return  "redirect:"+ URLDecoder.decode(returnUrl,"UTF-8")+"?openid="+openId;

    }

    //测试是否获得openid
    @RequestMapping("testOpenid")
    public void testOpenid(@RequestParam("openid")String openid){
        log.info("[获得用户的openid为]:{}",openid);
    }



}

