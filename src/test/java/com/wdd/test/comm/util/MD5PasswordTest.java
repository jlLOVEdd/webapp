package com.wdd.test.comm.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class MD5PasswordTest {
    MD5Password md5Password = new MD5Password();
    @Test
    public void MD5test() throws Exception {
        md5Password.MD5test();
    }

    public static void main(String[] args) {
        String reqUrl = "http://127.0.0.1:8080/webapp/testForUpdate/testUpdate";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(reqUrl);
        httpPost.addHeader("Content-Type","application/json; charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity("", Charset.forName("UTF-8")));
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed:" + response.getStatusLine());
            }else{
                String resultStr = EntityUtils.toString(response.getEntity());
                JSONObject resultJSON=JSONObject.parseObject(resultStr);
                System.out.println("resultJSON:"+resultJSON);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}