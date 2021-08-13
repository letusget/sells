package com.lll.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 随机生成主键工具类
 */

public class KeyUtil
{


    /**
     * 使用 时间 + 随机数来生成唯一主键
     * 为了保证多线程安全，可以对该 类进行加锁：synchronized  防止多线程时出现重复
     */
    /* 
    public static synchronized String genUniqueKey()
    {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000; // 生成6位的随机数
        return System.currentTimeMillis() + String.valueOf(number);
    }
*/

    /**
     * 生成19位UUId，可以确保不重复
     */
    public static String[] chars = new String[]{ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };

    public static  String genUniqueKey8()
    {
        StringBuffer shortBuffer = new StringBuffer();
        //将生成的UUID的 "-" 替换为 " "
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //生成8位 UUID
        for (int i = 0; i < 19; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }


}
