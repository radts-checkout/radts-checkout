package com.mediinfo.radts.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.*;

public class HttpClient {

    public static String getHttpInfoByPost(String url,
                                           Map<String, String> map) {
        String result = null;
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String keyString = iterator.next();
            nvps.add(new BasicNameValuePair(keyString, map.get(keyString)));
        }
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.getParams().setIntParameter("http.socket.timeout", 60000);
        HttpPost post = new HttpPost(url);
        try {
            post.setEntity((HttpEntity) new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = client.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            return result;
        }
    }

    public static Map<String, Object> getHttpByPost(String url,
                                                    Map<String, String> map) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String keyString = iterator.next();
            nvps.add(new BasicNameValuePair(keyString, map.get(keyString)));
        }
        return getHttpByPost(url, nvps);
    }

    public static Map<String, Object> getHttpByPost(String url,
                                                    String[]... params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            for (String[] param : params) {

                nvps.add(new BasicNameValuePair(param[0], param[1]));
            }
        }
        return getHttpByPost(url, nvps);
    }

    /**
     * 请求返回状态码code 和 结果 result
     */
    public static Map<String, Object> getHttpByPost(String url,
                                                    List<NameValuePair> nvps) {
        Map<String, Object> map = new HashMap<String, Object>();
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.getParams().setIntParameter("http.socket.timeout", 10000);

        HttpPost post = new HttpPost(url);

        StringBuilder builder = new StringBuilder();
        String result = null;
        String responseStr = "";
        int responseCode = 0;

        try {
            post.setEntity((HttpEntity) new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = client.execute(post);
            map = doResponse(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

    public static Map<String, Object> getHttpByGet(String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.getParams().setIntParameter("http.socket.timeout", 10000);
        HttpGet get = new HttpGet(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        try {
            HttpResponse response = client.execute(get);

            map = doResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

    private static Map<String, Object> doResponse(HttpResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String line = "";
        String result = null;
        String responseStr = "";
        int responseCode = 0;
        try {

            HttpEntity entity = response.getEntity();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    entity.getContent(), "UTF-8"), 8 * 1024);

            StringBuilder builder = new StringBuilder();

            responseCode = response.getStatusLine().getStatusCode();

            switch (responseCode) {
                // 请求成功
                case HttpURLConnection.HTTP_OK:
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    responseStr = builder.toString();
                    result = "请求成功";
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    responseStr = builder.toString();
                    result = "错误请求";
                    break;
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    result = "服务器端错误";
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    result = "未找到指定的网址";
                    break;
                case HttpURLConnection.HTTP_BAD_GATEWAY:
                    result = "请求超时";
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", HttpURLConnection.HTTP_BAD_GATEWAY);
            map.put("result", "无法连接网络，请检查网络设置");
            map.put("response", "");
        } finally {
            map.put("code", responseCode);
            map.put("result", result);
            map.put("response", responseStr);
            System.out.println("result:" + result);
            System.out.println("responseStr:" + responseStr);
            System.out.println("responseCode:" + responseCode);
        }

        return map;
    }

	/*public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
		String url = ""; // http请求所访问的地址
		Map<String, String> reqData = new HashMap<String, String>();
		String timestampString = DateUtil.datetimeToString(new Date());
		String transIdString = "pad_1" + timestampString;
		StringBuffer signBuffer = new StringBuffer();
		signBuffer.append("timestamp").append(GlobalInfo.FLAG_EQUAL_SIGN)
				.append(timestampString).append(GlobalInfo.PARA_TRANS_ID)
				.append(GlobalInfo.FLAG_EQUAL_SIGN).append(transIdString)
				.append("secret").append(GlobalInfo.FLAG_EQUAL_SIGN)
				.append("mediinfo_123");
		Md5Util md5 = new Md5Util();

		reqData.put("timestamp", timestampString);
		reqData.put("transId", transIdString);
		reqData.put("sign", md5.encrypt(signBuffer.toString()));



		System.out.println("sign:" + md5.encrypt(signBuffer.toString()));
		// 发送请求包
		Map<String, Object> respData = client.getHttpByPost(url, reqData);
		String responseInfo = (String) respData.get("response");
		logger.info("resp:");
		logger.info(responseInfo);

	}*/

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        //String url = "http://192.168.1.41:8131/health/getOrgList"; // http请求所访问的地址
        String url = "http://localhost:8105/health/getOrgList"; // http请求所访问的地址

        Map<String, String> reqData = new HashMap<String, String>();
        JSONObject jsonData = new JSONObject();
        JSONObject paramJson = new JSONObject();
        jsonData.put("id","register-totalenlarg-month");
        jsonData.put("param",paramJson);
        paramJson.put("beginTime","2016-09-01");
        paramJson.put("endTime","2017-08-03");
        paramJson.put("orgCode","470003222");
        //logger.info(jsonData.toJSONString());
        reqData.put("param",jsonData.toJSONString());
        // 发送请求包
        Map<String, Object> respData = client.getHttpByPost(url, reqData);
        String responseInfo = (String) respData.get("response");

    }

    private static String getTransId(String appKey) {
        StringBuffer buf = new StringBuffer();
        buf.append(appKey);
        //buf.append(DateUtil.formatString(new Date(),
                //DateUtil.FORMAT_DATETIME_18));
        return buf.toString();
    }

}

