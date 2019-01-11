package com.mediinfo.radts.contorller;

import com.mediinfo.radts.pojo.Result;
import com.mediinfo.radts.pojo.ResultFactory;
import com.mediinfo.radts.pojo.User;
import com.mediinfo.radts.pojo.his.GUAHAOKSXX;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.springframework.data.domain.Example;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/yygh")
public class YYGHController {
    @CrossOrigin
    @RequestMapping(value = "DoGUAHAOKSXX", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result DoGUAHAOKSXX(@Valid @RequestBody GUAHAOKSXX KeShiInfo, HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }
        /*
        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            //String sessionId = getSessionId();
            //post.setHeader("SessionId", sessionId);
            //post.setHeader("appid", appid);

            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);

            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                //LogUtil.info("请求出错: "+statusCode);
                //isSuccess = false;
            }else{
                int retCode = 0;
                String sessendId = "";
                // 返回码中包含retCode及会话Id
                for(Header header : response.getAllHeaders()){
                    if(header.getName().equals("retcode")){
                        retCode = Integer.parseInt(header.getValue());
                    }
                    if(header.getName().equals("SessionId")){
                        sessendId = header.getValue();
                    }
                }

                if(ErrorCodeHelper.IAS_SUCCESS != retCode ){
                    // 日志打印
                    LogUtil.info("error return code,  sessionId: "sessendId"\t"+"retCode: "+retCode);
                    isSuccess = false;
                }else{
                    isSuccess = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //isSuccess = false;
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //return isSuccess;
        */



        return ResultFactory.buildSuccessResult(request.getSession().getId());
    }
}
