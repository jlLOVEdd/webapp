package com.wdd.test.service.shiro;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.wdd.test.bean.User;
import com.wdd.test.service.user.UserService;


@Consumes
public class securityRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken)token;
		String userName = userToken.getUsername();
		User user = userService.getUserByname(userName);
		if(user==null){
			return null;
			//throw new UnknownAccountException("用户不存在");
		}
		String passWord = userService.getUserByname(userName).getPw();
		//user.setUsername("weidongdong");
		//user.setPw(passWord);
		return new SimpleAuthenticationInfo(userName, passWord, getName());
	}
	
}
