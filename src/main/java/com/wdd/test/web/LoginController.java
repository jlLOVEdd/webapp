package com.wdd.test.web;

import com.alibaba.fastjson.JSONObject;
import com.wdd.test.comm.log.InnerLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller

public class LoginController extends BaseController {


    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InnerLog.info("GET" + "/login");
        response.sendRedirect(request.getContextPath() + "/");
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void dologin(@RequestParam("username") String username, @RequestParam("password") String password
            , HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
        InnerLog.info("/login");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.isRememberMe();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            //SecurityUtils.getSubject().getSession().setTimeout(60000);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            jsonObj.put("success", false);
            jsonObj.put("msg", "用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            jsonObj.put("success", false);
            jsonObj.put("msg", "密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            jsonObj.put("success", false);
            jsonObj.put("msg", "输入过多用户锁定");
        } finally {
            out.append(jsonObj.toString());
            out.flush();
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

}
