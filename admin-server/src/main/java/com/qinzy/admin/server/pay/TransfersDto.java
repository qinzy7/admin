package com.qinzy.admin.server.pay;

import lombok.Data;

/**
 * @author kejiang
 * @since 2019-10-22
 */
@Data
public class TransfersDto {

    /** 商户账号appid：申请商户号的appid或商户号绑定的appid */
    private String mch_appid;

    /** 商户号 */
    private String mchid;

    /** 随机字符串 */
    private String nonce_str;

    /** 签名 */
    private String sign;

    /** 商户订单号 */
    private String partner_trade_no;

    /** 用户openid */
    private String openid;

    /** 校验用户姓名选项：NO_CHECK：不校验真实姓名，FORCE_CHECK：强校验真实姓名 */
    private String check_name = "NO_CHECK";

    /** 金额，单位分 */
    private int amount;

    /** 企业付款备注 */
    private String desc;

    /** ip地址 */
    private String spbill_create_ip = "127.0.0.1";

    private String appkey;
}