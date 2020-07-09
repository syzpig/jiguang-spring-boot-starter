package com.jr.ranlo.jiguang.factory;

import cn.jmessage.api.common.model.message.MessageBody;
import cn.jmessage.api.common.model.message.MessagePayload;
import cn.jmessage.api.message.MessageType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 *@description ${description}
 *@date 2020/7/9 9:20
 *@author syz
 */
public class MessagePayloadFactory {

    public static MessagePayload getMessagePayload(String type,String msg) {
        switch (type) {
            //text
            case "1":
                JSONObject jsonObject = JSON.parseObject(msg);
                MessageBody messageBody = MessageBody.newBuilder().setText(JSON.toJSONString(msg)).build();
                return MessagePayload.newBuilder()
                        //发消息类型 text - 文本，image - 图片, custom - 自定义消息（msg_body为json对象即可，服务端不做校验）voice - 语音 （必填）
                        .setMessageType(MessageType.TEXT)
                        .setVersion(1)
                        //填用户名 目标id single填username group 填Group id chatroom 填chatroomid（必填）
                        .setTargetId(jsonObject.getString("targetId"))
                        // single - 个人，group - 群组 chatroom - 聊天室（必填）
                        .setTargetType(jsonObject.getString("targetType"))
                        //发送消息者的身份 可为“admin”，“user” （必填）
                        .setFromType(jsonObject.getString("fromType"))
                        //发送者的username （必填)
                        .setFromId(jsonObject.getString("fromId"))
                        .setMessageBody(messageBody)
                        .build();
            //image
            case "2":
                MessageBody imgMessageBody = new MessageBody.Builder()
                        .setMediaId("qiniu/image/r/A92D550D57464CDF5ADC0D79FBD46210")
                        .setMediaCrc32(4258069839L)
                        .setWidth(43)
                        .setHeight(44)
                        .setFormat("png")
                        .setFsize(2670)
                        .build();

                MessagePayload payload = MessagePayload.newBuilder()
                        .setVersion(1)
                        .setTargetType("single")
                        .setTargetId(null)
                        .setFromType("admin")
                        .setFromId("junit_admin")
                        .setMessageType(MessageType.IMAGE)
                        .setMessageBody(imgMessageBody)
                        .build();
                 return payload;
                //custom
            case "3":
                JSONObject customJsonObject = JSON.parseObject(msg);
                MessageBody customMessageBody = MessageBody.newBuilder().setText(JSON.toJSONString(msg)).build();
                return MessagePayload.newBuilder()
                        //发消息类型 text - 文本，image - 图片, custom - 自定义消息（msg_body为json对象即可，服务端不做校验）voice - 语音 （必填）
                        .setMessageType(MessageType.CUSTOM)
                        .setVersion(1)
                        //填用户名 目标id single填username group 填Group id chatroom 填chatroomid（必填）
                        .setTargetId(customJsonObject.getString("targetId"))
                        // single - 个人，group - 群组 chatroom - 聊天室（必填）
                        .setTargetType(customJsonObject.getString("targetType"))
                        //发送消息者的身份 可为“admin”，“user” （必填）
                        .setFromType(customJsonObject.getString("fromType"))
                        //发送者的username （必填)
                        .setFromId(customJsonObject.getString("fromId"))
                        .setMessageBody(customMessageBody)
                        .build();
            //voice
            case "4":
                JSONObject voiceJsonObject = JSON.parseObject(msg);
                MessageBody voiceMessageBody = MessageBody.newBuilder().setText(JSON.toJSONString(msg)).build();
                return MessagePayload.newBuilder()
                        //发消息类型 text - 文本，image - 图片, custom - 自定义消息（msg_body为json对象即可，服务端不做校验）voice - 语音 （必填）
                        .setMessageType(MessageType.CUSTOM)
                        .setVersion(1)
                        //填用户名 目标id single填username group 填Group id chatroom 填chatroomid（必填）
                        .setTargetId(voiceJsonObject.getString("targetId"))
                        // single - 个人，group - 群组 chatroom - 聊天室（必填）
                        .setTargetType(voiceJsonObject.getString("targetType"))
                        //发送消息者的身份 可为“admin”，“user” （必填）
                        .setFromType(voiceJsonObject.getString("fromType"))
                        //发送者的username （必填)
                        .setFromId(voiceJsonObject.getString("fromId"))
                        .setMessageBody(voiceMessageBody)
                        .build();
            default:
                return null;
        }
    }

}
