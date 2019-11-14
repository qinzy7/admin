package com.qinzy.admin.server.pay;

import com.qinzy.admin.server.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kejiang
 * @since 2019-10-22
 */
@Slf4j
public class WeChatPayUtil {

    public static void main(String[] args) {

        // 证书存放路径
        String certPath = "E:\\Downloads\\File\\yscm\\apiclient_cert.p12";

        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("mch_appid", "wx171cc0dacde13a8f"); // 申请商户号的appid或商户号绑定的appid
        paramMap.put("mchid", "1518340651");  // 商户号
        paramMap.put("openid", "oc4F81a4ZR7ZUJE6EJP1Okks4TdQ"); // 商户appid下，某用户的openid
        paramMap.put("amount", "100");  //企业付款金额，这里单位为分
        paramMap.put("check_name", "NO_CHECK");  //校验用户姓名选项：NO_CHECK：不校验真实姓名，FORCE_CHECK：强校验真实姓名
        paramMap.put("desc", "测试企业付款到零钱");  //  备注
        paramMap.put("partner_trade_no", "123456789123456789");  // 商户订单号：需要保证唯一性
        paramMap.put("nonce_str", UUIDUtils.getUUID().toUpperCase());  //  获取随机字符串
        paramMap.put("spbill_create_ip", "127.0.0.1");

        // 生成签名
        String sign = createSign(paramMap, "Ysbd51659338ysbd5165933800000000");
        log.info("sign:{}", sign);

        // 封装请求参数
        String reqXmlStr = "<xml>"
                + "<mchid>" + paramMap.get("mchid") + "</mchid>"
                + "<mch_appid>" + paramMap.get("mch_appid") + "</mch_appid>"
                + "<nonce_str>" + paramMap.get("nonce_str") + "</nonce_str>"
                + "<check_name>" + paramMap.get("check_name") + "</check_name>"
                + "<openid>" + paramMap.get("openid") + "</openid>"
                + "<amount>" + paramMap.get("amount") + "</amount>"
                + "<desc>" + paramMap.get("desc") + "</desc>"
                + "<sign>" + sign + "</sign>"
                + "<partner_trade_no>" + paramMap.get("partner_trade_no") + "</partner_trade_no>"
                + "<spbill_create_ip>" + paramMap.get("spbill_create_ip") + "</spbill_create_ip>"
                + "</xml>";
        log.info("request XML:{}", reqXmlStr);
    }

    public static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static String createSign(Map<String, String> params, String paternerKey) {
        ArrayList<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        sb.append("key=").append(paternerKey);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }

    /**
     * 加载证书
     */
    public static void initCert(String certPath, String mchId) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream stream = new FileInputStream(new File(certPath));
        keyStore.load(stream, mchId.toCharArray()); // 加载证书密码，默认为商户号

    }


    /**
     * 获取ip
     *
     * @param request http请求参数
     * @return 返回ip
     */
    public static String getIp(HttpServletRequest request) {
        if (request == null)
            return "";
        String ip = request.getHeader("X-Requested-For");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.split(",")[0];
    }

}