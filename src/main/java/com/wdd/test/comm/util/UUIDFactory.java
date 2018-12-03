package com.wdd.test.comm.util;


import com.wdd.test.comm.config.ConfigContains;
import org.joda.time.DateTime;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 生成UUID
 * 
 * @author Administrator
 *
 */
public class UUIDFactory {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 
	 * @return
	 */
	public static String getBatchNo(int length) {
		if (length < 0 || length > 10) {
			length = 10;
		}
		return new DateTime().toString(ConfigContains.FILE_DATETIME_PATTERN) + getRanDom(length);
	}

	static Random rand = new Random();

	public static String getRanDom(int length) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(rand.nextInt(10));
		}
		return buffer.toString();
	}


	public static Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,20}+$");
	/**
	 * 验证密码规则<br/>
	 * 1.密码不能为全数字；
	 * 2.密码不能为全字母：
	 * 3.密码只能为：数字1-9，字母a-z、A-Z 下划线
	 * @param password 密码字符
	 * @return true/false
	 */
	public static boolean checkPwd(String password){
		if(StringUtils.isBlank(password)){
			return false;
		}
		return pattern.matcher(password).find();
	}
	public static void main(String[] args) {
			System.out.println(new DateTime().toString(ConfigContains.FILE_DATETIME_PATTERN)+getRanDom(10));
		
	}

}
