//package com.wdd.test.comm.util;
//
//import com.alibaba.fastjson.JSONObject;
//
//import com.sun.deploy.net.HttpResponse;
//import org.junit.Test;
//import org.springframework.http.HttpStatus;
//import sun.net.www.http.HttpClient;
//
//import java.io.UnsupportedEncodingException;
//
//public class MD5PasswordTest {
//    MD5Password md5Password = new MD5Password();
//    @Test
//    public void MD5test() throws Exception {
//        md5Password.MD5test();
//    }
//
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String reqUrl = "http://127.0.0.1:8080/webapp/testForUpdate/testUpdate";
//
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost=new HttpPost(reqUrl);
//        httpPost.addHeader("Content-Type","application/json; charset=utf-8");
//        httpPost.setHeader("Accept", "application/json");
//        //httpPost.setEntity(new StringEntity("", Charset.forName("UTF-8")));
//        try {
//            HttpResponse response = httpClient.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpStatus.SC_OK) {
//                System.err.println("Method failed:" + response.getStatusLine());
//            }else{
//                String resultStr = EntityUtils.toString(response.getEntity());
//                JSONObject resultJSON=JSONObject.parseObject(resultStr);
//                System.out.println("resultJSON:"+resultJSON);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}