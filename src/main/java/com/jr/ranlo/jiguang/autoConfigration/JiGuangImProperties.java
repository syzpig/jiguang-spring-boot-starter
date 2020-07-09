package com.jr.ranlo.jiguang.autoConfigration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 *@description ${description}
 *@date 2020/7/9 9:20
 *@author syz
 */
@ConfigurationProperties(prefix = "jr.ranlo.jiguang.im")
public class JiGuangImProperties {
    private String appkey;
    private String masterSecret;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }
}
