package com.qinzy.admin.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author qinzy7@163.com
 * @since 2019-10-12
 */
public class UUIDUtils {
    /**
     * 生成不带-的UUID
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成时间加随机数的20位UUID
     * @return UUID
     */
    public static String getTimeUUID() {
        /*生成随机数:当前精确到秒的时间再加6位的数字随机序列*/
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String rdNum = df.format(new Date());
        Random random = new Random();
        int ird = random.nextInt(999999);
        String srd = String.format("%06d", ird);
        // 返回UUID
        return rdNum + srd;
    }
}