# MailSender  

## 简介  
MailSender基于[JavaMail for Android](https://javaee.github.io/javamail/Android)开发，旨在帮助开发者在Android平台快速实现邮件发送  
## 特点  
* Kotlin开发，兼容Java项目
* 支持发送纯文本、html、SpannableString内容邮件发送
* 支持发送带附件邮件
* 支持抄送，密送
  
## 集成  
[![](https://jitpack.io/v/ZhySir/MailSender.svg)](https://jitpack.io/#ZhySir/MailSender) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)  
To get a Git project into your build:  
Step 1. Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:  
```
allprojects {
  repositories {
    ...
    maven{url'https://jitpack.io'}
  }
}
```  
Step 2. Add the dependency  
```
dependencies {
    implementation 'com.github.ZhySir:MailSender:1.0.0'
}
```
  
## Mail说明  
| 属性             | 说明                               | 是否必须 |
|:-------------- |:------------------------------------ |:------ |
| mailServerHost| 发件邮箱服务器         | true   |
| mailServerPort | 发件邮箱服务器端口      | true   |
| fromAddress    | 发件邮箱地址     | true   |
| password     | 发件箱授权码（密码）     | true   |
| toAddress     | 直接收件人邮箱    | true   |
| ccAddress     | 抄送者邮箱     | false   |
| bccAddress     | 密送者邮箱     | false   |
| subject     | 邮件主题     | false   |
| content     | 邮件内容     | false   |
| attachFiles     | 附件     | false   |
  
## 关于授权码的获取  
[QQ邮箱获取授权码?](https://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256)  
[网易邮箱获取授权码?](https://help.mail.163.com/faq.do?m=list&categoryID=197)  
..  
