package com.qinzy.admin.server.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qinzy7@163.com
 * @since 2019-10-29
 */
@Slf4j
public class HttpClientUtil {

    /**
     * get请求
     *
     * @param url    请求路径
     * @param params 参数
     * @return 请求结果
     */
    public static JSONObject doGet(String url, Map<String, String> params) {

        // 返回结果
        JSONObject result = null;
        // 创建HttpClient对象
        CloseableHttpClient httpClient;
//        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;
        try {
            httpClient = SSLClientCustom.getHttpClient();
            // 拼接参数,可以用URIBuilder,也可以直接拼接在？传值，拼在url后面，如下--httpGet = new
            // HttpGet(uri+"?id=123");
            URIBuilder uriBuilder = new URIBuilder(url);
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                    // 或者用
                    // 顺便说一下不同(setParameter会覆盖同名参数的值，addParameter则不会)
                    // uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            // 创建get请求
            httpGet = new HttpGet(uri);
            log.info("访问路径：" + uri);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 返回200，请求成功
                // 结果返回
                result = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
                log.info("请求成功！，返回数据：" + result);
            } else {
                log.info("请求失败！");
            }
        } catch (Exception e) {
            log.info("请求失败!");
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            // 释放连接
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
        return result;
    }

    /**
     * post请求
     *
     * @param url    请求路径
     * @param params 参数
     * @return 请求结果
     */
    public static String doPost(String url, Map<String, String> params) {
        String result = "";
        // 创建httpclient对象
        // 创建HttpClient对象
        CloseableHttpClient httpClient;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            // 参数键值对
            httpClient = SSLClientCustom.getHttpClient();
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                NameValuePair pair = null;
                for (String key : params.keySet()) {
                    pair = new BasicNameValuePair(key, params.get(key));
                    pairs.add(pair);
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("返回数据：>>>" + result);
            } else {
                log.info("请求失败！，url:" + url);
            }
        } catch (Exception e) {
            log.error("请求失败");
            log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        } finally {
            if (null != httpPost) {
                // 释放连接
                httpPost.releaseConnection();
            }
        }
        return result;
    }

    /**
     * post发送json字符串
     *
     * @param url    请求路径
     * @param params 参数
     * @return 请求结果
     */
    public static String sendJsonStr(String url, String params) {
        String result = "";

        HttpClient httpClient;

        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpClient = SSLClientCustom.getHttpClient();
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            if (StringUtils.isNotBlank(params)) {
                httpPost.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
                log.info("返回数据：" + result);
            } else {
                log.info("请求失败");
            }
        } catch (Exception e) {
            log.error("请求异常");
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (null != httpPost) {
                // 释放连接
                httpPost.releaseConnection();
            }
        }
        return result;
    }

    /**
     * post发送xml字符串
     *
     * @param url        请求路径
     * @param requestXml xml参数
     * @return 请求结果
     */
    public static String sendXmlStr(String url, String requestXml) {
        String result = "";
        HttpPost httpPost = null;
        try {
            // 定义HttpPost请求
            httpPost = new HttpPost(url);
            // 定义HttpClient
            HttpClient httpClient = SSLClientCustom.getHttpClient();
            // 定义请求实体
            if (StringUtils.isNotBlank(requestXml)) {
                httpPost.setEntity(new StringEntity(requestXml, "gbk"));
            }

            httpPost.addHeader("Pragma:", "no-cache");
            httpPost.addHeader("Cache-Control", "no-cache");
            httpPost.addHeader("Content-Type", "application/fox");
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 以post方式发送Http请求
            HttpResponse response = httpClient.execute(httpPost);
            // 获取响应实体
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
                log.info("返回数据：" + result);
            } else {
                log.info("请求失败");
            }
        } catch (Exception e) {
            log.error("请求异常");
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (null != httpPost) {
                // 释放连接
                httpPost.releaseConnection();
            }
        }
        return result;
    }

}