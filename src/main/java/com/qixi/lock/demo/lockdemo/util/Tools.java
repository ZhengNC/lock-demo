package com.qixi.lock.demo.lockdemo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	/**
	 * 验证字符串是否为null、"null"、""、纯由空格或缩进组成
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str) || "null".equals(str)) {
			return true;
		}
		for(Character c:str.toCharArray()) {
			if(!" ".equals(c.toString()) && !"	".equals(c.toString())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 去除字符串头尾的空格和缩进
	 * @param str
	 * @return
	 */
	public static String processStr(String str){
		if (str == null){
			return null;
		}
		if ("".equals(str)){
			return "";
		}
		while (str.startsWith(" ") || str.startsWith("	")){
			str = str.substring(1, str.length());
		}
		while (str.endsWith(" ") || str.endsWith("	")){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	/**
	 * 得到随机的四位验证码
	 * @return
	 */
	public static String getMsgCode() {
		int a = (int) (Math.random() * 10000);
		String s = Integer.toString(a);
		int sLength = s.length();
		for(int i = 0; i < 4 - sLength; i++) {
			s = "0" + s;
		}
		return s;
	}

	/**
	 * 判断字符串内容是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		if(!Tools.isEmpty(str)){
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (isNum.matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取指定长度的随机字符串
	 * @param length （1 - 32）传空默认为32
	 * @param upperOrLower （upper或lower）传null默认为不转换
	 * @return
	 */
	public static String randomStr(Integer length, String upperOrLower){
		if (length != null){
			if (length < 1 || length > 32){
				throw new RuntimeException("字符串长度设置错误");
			}
		}
		if (upperOrLower != null && !"upper".equals(upperOrLower) && !"lower".equals(upperOrLower)){
			throw new RuntimeException("字符串转换方式设置错误");
		}
		String result = UUID.randomUUID().toString().replace("-","");
		if (length != null){
			result = result.substring(0, length);
		}
		if ("upper".equals(upperOrLower)){
			result = result.toUpperCase();
		}
		if ("lower".equals(upperOrLower)){
			result = result.toLowerCase();
		}
		return result;
	}

	/**
	 * 从请求里取参数（顺序为：header,param,cookie）
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getParamByRequest(HttpServletRequest request, String name){
		if (Tools.isEmpty(name)){
			return null;
		}
		String param = request.getHeader(name);
		if (Tools.isEmpty(param)){
			param = request.getParameter(name);
		}
		if (Tools.isEmpty(param)){
			Cookie[] cookies = request.getCookies();
			if (cookies!= null){
				for(Cookie cookie : cookies){
					if (name.equals(cookie.getName())){
						param = cookie.getValue();
						break;
					}
				}
			}
		}
		return param;
	}

	/**
	 * 获取一个随机数，范围在[min, max]
	 * @param min 最小数（包括）
	 * @param max 最大数（包括）
	 * @return
	 */
	public static int randomNum(int min, int max){
		if (min < 0 || max < 0 || min > max){
			throw new RuntimeException("选择的范围错误");
		}
		int result = (int)(Math.random() * (max - min + 1)) + min;
		return result;
	}

	public static void main(String[] args){
		Arrays.stream(new int[10]).forEach(i -> {
			System.out.println(randomNum(1, 10));
		});
	}
}
