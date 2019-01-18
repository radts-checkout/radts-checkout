package com.mediinfo.radts.util;

import com.mediinfo.radts.pojo.Email;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailUtil {
    
    public void sendEmail(String user, String password, List<String> userList, Email emailInfo){

        try{
            Properties properties = new Properties();
            properties.put("mail.transport.protocol", "smtp");// 连接协议
            properties.put("mail.smtp.host", "smtp.126.com");// 主机名
            properties.put("mail.smtp.port", 465);// 端口号
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
            //properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
            // 得到回话对象
            Session session = Session.getInstance(properties);
            // 获取邮件对象
            Message message = new MimeMessage(session);
            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress(user));
            // 设置收件人邮箱地址
            InternetAddress[] internetAddress = new InternetAddress[userList.size()];
            for (int i = 0; i < userList.size(); i++) {
                internetAddress[i] = new InternetAddress(userList.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, internetAddress);
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
            // 设置邮件标题
            message.setSubject(emailInfo.getFromName() );
            // 设置邮件内容
            message.setText(emailInfo.getFromMsg() + " from: " + emailInfo.getFromEmail() + ")");
            // 得到邮差对象
            Transport transport = session.getTransport();
            // 连接自己的邮箱账户
            transport.connect(user, password);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
