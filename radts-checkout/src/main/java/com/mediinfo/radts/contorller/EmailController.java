package com.mediinfo.radts.contorller;

import com.mediinfo.radts.pojo.Email;
import com.mediinfo.radts.pojo.Result;
import com.mediinfo.radts.pojo.ResultFactory;
import com.mediinfo.radts.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class EmailController {

    /**
     * 获取application.yml中的配置
     */
    @Value("${email.user}")
    private String user;

    @Value("${email.password}")
    private String password;

    @Value("${email.touserlist}")
    private String userList;

    @CrossOrigin
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result sendEmail(@Valid @RequestBody Email emailInfo, HttpServletRequest request, BindingResult bindingResult) {
        try{
            List<String> userlist = new ArrayList<>();
            for (String userItem:userList.split(",")) {
                userlist.add(userItem);
                log.debug(userItem);
            }
            new EmailUtil().sendEmail(user, password, userlist, emailInfo);
            return ResultFactory.buildSuccessResult("发送成功");
        }catch(Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }


    }

}
