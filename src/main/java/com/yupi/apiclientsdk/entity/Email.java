package com.yupi.apiclientsdk.entity;

import lombok.Data;

@Data
public class Email {
    private String subject; // 邮件标题
    private String text; // 发送内容
    private String to; // 发送给谁
}
