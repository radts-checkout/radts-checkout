package com.mediinfo.radts.contorller;

import com.mediinfo.radts.pojo.Email;
import com.mediinfo.radts.pojo.Result;
import com.mediinfo.radts.pojo.ResultFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Properties;

@RestController
@RequestMapping("/api")
public class EmailController {
    @CrossOrigin
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result sendEmail(@Valid @RequestBody Email emailInfo, HttpServletRequest request, BindingResult bindingResult) {
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
            message.setFrom(new InternetAddress("yanghongqi23@126.com"));
            // 设置收件人邮箱地址
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("yanghongqi@mediinfo.com.cn")});
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
            // 设置邮件标题
            message.setSubject(emailInfo.getFromName() );
            // 设置邮件内容
            message.setText(emailInfo.getFromMsg() + " from: " + emailInfo.getFromEmail() + ")");
            // 得到邮差对象
            Transport transport = session.getTransport();
            // 连接自己的邮箱账户
            transport.connect("yanghongqi23@126.com", "hhbvaj23");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return ResultFactory.buildSuccessResult("发送成功");
        }catch(Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }


    }

}
