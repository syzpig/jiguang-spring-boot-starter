package com.jr.ranlo.jiguang.dto;


import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;

/**
 * @author syz
 * @description 生成IM鉴权数据载体
 * @date 2019/6/17 11:57
 */
public class IMAuthPayloadDto implements Serializable {
    private static final long serialVersionUID = 6228431914544155261L;

    private String appkey;      // 开发者在极光平台注册的 IM 应用 appkey
    private String random_str;   // 20-36 长度的随机字符串, 作为签名加 salt 使用
    private Long timestamp;   // 当前时间戳，用于防止重放攻击，精确到毫秒
    private String signature;   //签名，10 分钟后失效（只针对初始化操作，初始化成功则之后的操作跟签名无关）

    public IMAuthPayloadDto() {
        this.random_str = RandomStringUtils.randomAlphanumeric(30);
        this.timestamp = System.currentTimeMillis();
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getRandom_str() {
        return random_str;
    }

    public void setRandom_str(String random_str) {
        this.random_str = random_str;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
