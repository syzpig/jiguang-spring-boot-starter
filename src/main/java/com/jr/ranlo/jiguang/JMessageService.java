package com.jr.ranlo.jiguang;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.common.model.message.MessagePayload;
import cn.jmessage.api.message.MessageClient;
import cn.jmessage.api.message.SendMessageResult;
import com.jr.ranlo.jiguang.autoConfigration.JiGuangImProperties;
import com.jr.ranlo.jiguang.dto.IMAuthPayloadDto;
import com.jr.ranlo.jiguang.factory.MessagePayloadFactory;
import com.jr.ranlo.jiguang.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author syz
 * @description 极光JAVA ADK API 推送
 * @date 2019/5/23 10:22
 */
public class JMessageService {
    private static final Logger log = LoggerFactory.getLogger(JMessageService.class);

    @Autowired
    private JiGuangImProperties jiGuangImProperties;

    /**
     * @param
     * @return
     * @description 初始化极光客户端
     */
    private MessageClient createJMessageClient() {
        MessageClient client = null;
        try {
            client = new MessageClient(jiGuangImProperties.getAppkey(), jiGuangImProperties.getMasterSecret());
        } catch (Exception e) {
            log.error("初始化极光客户端失败！请确认配置是否正确！错误描述：" + e.toString());
        }
        return client;
    }


    /**
     * @param
     * @return
     * @description 发送自定义消息
     */
    public void sendMessage(String type, String msg) {
        try {
            MessagePayload messagePayload = MessagePayloadFactory.getMessagePayload(type, msg);
            log.info("content=" + messagePayload.toString());
            SendMessageResult res = createJMessageClient().sendMessage(messagePayload);
            if (res.isResultOK()) {
                log.info("发送极光通知结束!响应code=" + res.getResponseCode() + ";返回结果=" + res.toString());
            } else {
                log.error("发送极光通知结束!响应code=" + res.getResponseCode() + ";返回结果=" + res.toString());
            }
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.error("HTTP Status: " + e.getStatus());
            log.error("Error Message: " + e.getMessage());
        } catch (Exception e) {
            log.error("发送极光通知失败！错误描述：" + e.toString());
        }
    }

    /**
     * @param
     * @return
     * @description ${description}
     */
    public IMAuthPayloadDto buildAuthPayload() {
        IMAuthPayloadDto payloadDto = new IMAuthPayloadDto();
        String signature = MD5Util.md5("appkey=" + jiGuangImProperties.getAppkey() + "&timestamp=" + payloadDto.getTimestamp() + "&random_str=" + payloadDto.getRandom_str() + "&key=" + jiGuangImProperties.getMasterSecret());
        payloadDto.setAppkey(jiGuangImProperties.getAppkey());
        payloadDto.setSignature(signature);
        return payloadDto;
    }
}
