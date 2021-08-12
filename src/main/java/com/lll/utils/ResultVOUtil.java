package com.lll.utils;

import com.lll.vo.ResultVO;

/**
 * HTTP 请求返回的最外层对象 ResultVO 工具类  --ResultVOUtil
 * 封装工具类
 */
public class ResultVOUtil
{
    /**
     * JSON 成功返回的数据
     */
    public static ResultVO success(Object object)
    {
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    //重载方法
    public static ResultVO success(Integer code,String msg)
    {
        ResultVO resultVO =new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO success()
    {
        return success(null);
    }


}
