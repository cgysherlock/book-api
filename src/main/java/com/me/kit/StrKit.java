package com.me.kit;

import java.util.ArrayList;

import java.util.List;

public class StrKit {

	/**
	 * 判断不为空
	 * 
	 * @param para
	 * @return
	 */
	public static boolean notBlank(String para) {
		if (para != null && !para.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断为空
	 * 
	 * @param para
	 * @return
	 */
	public static boolean isBlank(String para) {
		if (para == null || para.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查字符串是否在List中
	 * 
	 * @param str
	 * @param list
	 * @return
	 */
	public static boolean checkStringInList(String str, List<String> list) {
		for (String s : list) {
			if (s.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得不在str2[]中的str1[]中的字符串
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static List<String> getStrNotInStr(String[] str1, String[] str2) {
		List<String> list = new ArrayList<String>();
		int flag = 0;
		int length2 = str2.length;
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < length2; j++) {
				if (!str1[i].equals(str2[j])) {
					flag++;
				}
			}
			if (flag == length2) {
				list.add(str1[i]);
			}
			flag = 0;
		}
		return list;
	}

	/**
	 * 把字符串List转化为String以","分割
	 * 
	 * @param list
	 * @return
	 */
	public static String changeStr(List<String> list) {
		String str = "";
		for (String s : list) {
			str = str + "," + s;
		}
		if (str.equals("")) {
			return "";
		}
		return str.substring(1);
	}

	/**
	 * 合并字符串数组
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String join(String[] str, String separator) {
		StringBuffer string = new StringBuffer();

		for (int i = 0; i < str.length; i++) {
			if (i != str.length - 1) {
				string.append(str[i] + separator);
			} else {
				string.append(str[i]);
			}
		}
		return string.toString();
	}
	
	/**
	 * 获得指定长度的数字字符串
	 * @param length
	 * @return
	 */
	public static String generatorNumberCode(int length) {
		int number = (int) Math.pow(10, length-1);
		return String.valueOf(Math.round(((Math.random()*9+1)*number)));
	}

}
