package com.yupi.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.apiclientsdk.entity.Email;
import com.yupi.apiclientsdk.entity.User;
import com.yupi.apiclientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ApiClient {

    private static final String GATEWAY_HOST = "http://39.107.240.179:8090";

    private String accessKey;
    private String secretKey;

    private static final String BODY = "codeqiu";

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println("result = " + result);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println("result = " + result);
        return result;
    }

    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .addHeaders(getHeaderKey(json))
                .body(json)
                .execute();
        System.out.println(execute.getStatus());
        String result = execute.body();
        return result;
    }

    public String getLoverPrattle() {
        HttpResponse execute = HttpRequest.post(GATEWAY_HOST + "/api/getLoverPrattle")
                .addHeaders(getHeaderKey(BODY))
                .body(BODY)
                .execute();
        System.out.println(execute.getStatus());
        String result = execute.body();
        return result;
    }

    public String sendEmail(Email email) {
        String json = JSONUtil.toJsonStr(email);
        HttpResponse execute = HttpRequest.post(GATEWAY_HOST + "GATEWAY_HOST/api/email")
                .addHeaders(getHeaderKey(json))
                .body(json)
                .execute();
        String result = execute.body();
        return result;
    }

    public String getdyGirlGet() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/dygirl")
                .addHeaders(getHeaderKey(BODY))
                .body(BODY)
                .execute();
        String result = httpResponse.body();
        return result;
    }

    //随机返回爬虫美女视频
    public String getwyrp() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/wyrp")
                .addHeaders(getHeaderKey(BODY))
                .body(BODY)
                .execute();
        String result = httpResponse.body();
        return result;
    }

    private Map<String, String> getHeaderKey(String body) {
        Map<String, String> headerMap = new HashMap<>();
        try {
            headerMap.put("accessKey", this.accessKey);
            // 一定不能直接发送
//        headerMap.put("secretKey", this.secretKey);
            headerMap.put("nonce", RandomUtil.randomNumbers(4));
            headerMap.put("body", URLEncoder.encode(body, "utf-8"));
            headerMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
            headerMap.put("sign", SignUtils.genSign(body, secretKey));
        } catch (UnsupportedEncodingException e) {
            log.error("body 出错");
        }
        return headerMap;
    }


}
