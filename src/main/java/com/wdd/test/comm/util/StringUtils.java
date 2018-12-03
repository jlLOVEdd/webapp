/**
 * Copyright (c) 2014 ShanghaiMed iKang,Inc. All rights reserved.
 */
package com.wdd.test.comm.util;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承{@link org.apache.commons.lang.StringUtils}.
 * 
 * @author <a href="mailto:jason.wu@ikang.com">Wu Jun</a>
 * @version 1.0, 10/13/2014
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 字符集定义.
	 */
	public enum Charset {
		
		/** UTF-8 */
		UTF_8("UTF-8"),

		/** GBK */
		GBK("GBK"),

		/** GB2312 */
		GB2312("GB2312"),
		
		/** ISO-8859-1 */
		ISO_8859_1("ISO-8859-1"),

		/** US-ASCII */
		US_ASCII("US-ASCII"),

		;

		private String name;

		private Charset(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	/**
	 * format string pattern.
	 * ex: StringUtils.format("a:{0}, b:{1}", "v1", "v3");
	 * 
	 * @param format
	 * @param args
	 * @return
	 */
	public static String format(String format, Object... args) {
		return MessageFormat.format(format, args);
	}

	public static String lowerFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	public static String upperFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）.
	 * 
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）.
	 * 
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
		return abbr(replaceHtml(str), length);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Unescapes a string containing entity escapes to a string containing the actual Unicode
	 * characters corresponding to the escapes. Supports HTML 4.0 entities.
	 * </p>
	 * <p>
	 * For example, the string "&amp;lt;Fran&amp;ccedil;ais&amp;gt;" will become
	 * "&lt;Fran&ccedil;ais&gt;"
	 * </p>
	 * <p>
	 * If an entity is unrecognized, it is left alone, and inserted verbatim into the result string.
	 * e.g. "&amp;gt;&amp;zzzz;x" will become "&gt;&amp;zzzz;x".
	 * </p>
	 *
	 * @param str the <code>String</code> to unescape, may be null
	 * @return a new unescaped <code>String</code>, <code>null</code> if null string input
	 * @see #escapeHtml(Writer, String)
	 */
	public static String escapeHtml(String input) {
		return StringEscapeUtils.unescapeHtml(input);
	}

	/**
	 * <p>
	 * Escapes the characters in a <code>String</code> using JavaScript String rules.
	 * </p>
	 * <p>
	 * Escapes any values it finds into their JavaScript String form. Deals correctly with quotes
	 * and control-chars (tab, backslash, cr, ff, etc.)
	 * </p>
	 * <p>
	 * So a tab becomes the characters <code>'\\'</code> and <code>'t'</code>.
	 * </p>
	 * <p>
	 * The only difference between Java strings and JavaScript strings is that in JavaScript, a
	 * single quote must be escaped.
	 * </p>
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * input string: He didn't say, "Stop!"
	 * output string: He didn\'t say, \"Stop!\"
	 * </pre>
	 * 
	 * </p>
	 *
	 * @param str String to escape values in, may be null
	 * @return String with escaped values, <code>null</code> if null string input
	 */
	public static String escapeJavaScript(String input) {
		return StringEscapeUtils.escapeJavaScript(input);
	}

	/**
	 * <p>
	 * Escapes the characters in a <code>String</code> to be suitable to pass to an SQL query.
	 * </p>
	 * <p>
	 * For example,
	 * 
	 * <pre>
	 * statement.executeQuery(&quot;SELECT * FROM MOVIES WHERE TITLE='&quot; + StringEscapeUtils.escapeSql(&quot;McHale's Navy&quot;)
	 * 		+ &quot;'&quot;);
	 * </pre>
	 * 
	 * </p>
	 * <p>
	 * At present, this method only turns single-quotes into doubled single-quotes (
	 * <code>"McHale's Navy"</code> => <code>"McHale''s Navy"</code>). It does not handle the cases
	 * of percent (%) or underscore (_) for use in LIKE clauses.
	 * </p>
	 * see http://www.jguru.com/faq/view.jsp?EID=8881
	 * 
	 * @param str the string to escape, may be null
	 * @return a new String, escaped for SQL, <code>null</code> if null string input
	 */
	public static String escapeSql(String str) {
		if (str == null) {
			return null;
		}
		return StringUtils.replace(str, "'", "''");
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

}
