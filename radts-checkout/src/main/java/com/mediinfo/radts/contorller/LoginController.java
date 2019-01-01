package com.mediinfo.radts.contorller;

import javax.validation.Valid;

import com.mediinfo.radts.dao.UserDao;
import com.mediinfo.radts.pojo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mediinfo.radts.pojo.Result;
import com.mediinfo.radts.pojo.User;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserDao userDao;
    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */
    @CrossOrigin
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@Valid @RequestBody User userInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }

        if (userDao.findAll(Example.of(userInfo)).size() > 0) {
            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
            return ResultFactory.buildFailResult(message);
        }
        //if (!Objects.equals("admin", userInfo.getUsername()) || !Objects.equals("admin", userInfo.getPassword())) {
            //String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
            //return ResultFactory.buildFailResult(message);
        ///}
        return ResultFactory.buildSuccessResult("登陆成功。");
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") String id) {
        Optional<User> user = userDao.findById(id);
        return user.get();
    }
    @PostMapping("")
    public User add(User user) {

        return userDao.save(user);
    }
    @PutMapping("")
    public User update(User user) {

        return userDao.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        userDao.deleteById(id);
    }

}
