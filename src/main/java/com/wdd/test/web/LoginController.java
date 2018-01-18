package com.wdd.test.web;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.impl.Log4jLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wdd.test.bean.Role;
import com.wdd.test.bean.User;
import com.wdd.test.comm.DataCacheManager;
import com.wdd.test.service.LoginControllerService;

@Controller

public class LoginController extends BaseController{
	@Autowired 
	CacheManager cacheManager;
	@Autowired
	private LoginControllerService loginControllerService;

	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login(){
			return "login";
		}
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void dologin(@RequestParam("username") String username, @RequestParam("password") String password
			, HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		System.out.println("/login");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonObj = new JSONObject();
		//System.out.println(request.getHeader("X-Requested-With"));
		jsonObj.put("success",true);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.isRememberMe();
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			jsonObj.put("success",false);
			jsonObj.put("msg","用户名错误");
		} catch (IncorrectCredentialsException e) {
			jsonObj.put("success",false);
			jsonObj.put("msg","密码错误");
		} catch (AuthenticationException e) {
			jsonObj.put("success",false);
			jsonObj.put("msg","输入过多用户锁定");
		}finally {
			out.append(jsonObj.toString());
			out.flush();
			if(out!=null){
				out.close();
			}
		}
	}

	@RequestMapping(value = "/main")
	public String main(){
		return "main";
	}
}
