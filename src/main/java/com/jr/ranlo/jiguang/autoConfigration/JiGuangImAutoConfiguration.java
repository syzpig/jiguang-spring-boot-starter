package com.jr.ranlo.jiguang.autoConfigration;


import com.jr.ranlo.jiguang.JMessageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *@description ${description}
 *@date 2020/7/9 9:20
 *@author syz
 */
@EnableConfigurationProperties(JiGuangImProperties.class)
@Configuration
public class JiGuangImAutoConfiguration {
    @Bean
    public JMessageService jmessageService() {
        return new JMessageService();
    }
}
