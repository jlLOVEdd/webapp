package com.wdd.test.web;

import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

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
		System.out.println("turn down loginWin.jsp");
			return "login";
		}
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView dologin(@RequestParam("username") String username,@RequestParam("password")String password
			,HttpServletRequest request,Model model) throws Exception{
		//System.out.println(" turn down index.jsp");
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		token.isRememberMe();
		Subject subject =SecurityUtils.getSubject();
		ModelAndView mav = new ModelAndView("index");
		try{
		subject.login(token);
		}catch(UnknownAccountException e){
			model.addAttribute("error", "用户名不存在");
			mav.setViewName("loginWin");
			e.printStackTrace();
			//throw new Exception("用户名不存在");
			return mav;
			
		}catch(IncorrectCredentialsException e){
			model.addAttribute("error", "密码错误");
			mav.setViewName("loginWin");
			e.printStackTrace();
			//throw new Exception("用户名不存在");
		}catch(AuthenticationException e){
			model.addAttribute("error", "输入密码次数过多，用户");
			mav.setViewName("loginWin");
			e.printStackTrace();
			//throw new Exception("用户名不存在");
		}
		return mav;
		
			
		
	}
}
