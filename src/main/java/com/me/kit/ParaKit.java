package com.me.kit;

import java.util.ArrayList;
import java.util.List;
public class ParaKit {

	/**
	 * 自动匹配值
	 * 
	 * @param request
	 * @param model
	 */
	// public static void getPara(HttpServletRequest request, Model model){
	// Map<String, String[]> map = request.getParameterMap();
	// for(String para : map.keySet()){
	// for(Field f : model.getClass().getDeclaredFields()){
	// if(para.equals(f.getName())){
	// try {
	// f.setAccessible(true);
	// if(map.get(para).length>1){
	// setFieldValue(f, model, map.get(para));
	// }else{
	// setFieldValue(f, model, map.get(para)[0]);
	// }
	// f.setAccessible(false);
	// } catch (IllegalArgumentException | IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	// }

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
	 * 转换参数类型
	 * 
	 * @param f
	 * @param m
	 * @param para
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	// public static void setFieldValue(Field f, Model m, String para) throws
	// IllegalArgumentException, IllegalAccessException{
	// Class<?> type = f.getType();
	// String typeName = type.getName();
	// if(typeName.equals("int")){
	// f.set(m, Integer.parseInt(para));
	// }else if(typeName.equals("long")){
	// f.set(m, Long.parseLong(para));
	// }else if(typeName.equals("double")){
	// f.set(m, Double.parseDouble(para));
	// }else if(typeName.equals("float")){
	// f.set(m, Float.parseFloat(para));
	// }else if(typeName.equals("short")){
	// f.set(m, Short.parseShort(para));
	// }else{
	// f.set(m, type.cast(para));
	// }
	// }

	/**
	 * 转换数组类型
	 * 
	 * @param f
	 * @param m
	 * @param para
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	// public static void setFieldValue(Field f, Model m, String[] para) throws
	// IllegalArgumentException, IllegalAccessException{
	// Class<?> type = f.getType();
	// String typeName = type.getName();
	// System.out.println("typeName="+typeName);
	// if(typeName.equals("[I")){
	// int[] paras = new int[para.length];
	// for(int i=0;i<para.length;i++){
	// paras[i] = Integer.parseInt(para[i]);
	// }
	// f.set(m, paras);
	//
	// }else if(typeName.equals("[J")){
	// long[] paras = new long[para.length];
	// for(int i=0;i<para.length;i++){
	// paras[i] = Long.parseLong(para[i]);
	// }
	// f.set(m, paras);
	// }else if(typeName.equals("[D")){
	// double[] paras = new double[para.length];
	// for(int i=0;i<para.length;i++){
	// paras[i] = Double.parseDouble(para[i]);
	// }
	// f.set(m, paras);
	// }else if(typeName.equals("[F")){
	// float[] paras = new float[para.length];
	// for(int i=0;i<para.length;i++){
	// paras[i] = Float.parseFloat(para[i]);
	// }
	// f.set(m, paras);
	// }else if(typeName.equals("[S")){
	// short[] paras = new short[para.length];
	// for(int i=0;i<para.length;i++){
	// paras[i] = Short.parseShort(para[i]);
	// }
	// f.set(m, paras);
	// }else{
	// f.set(m, type.cast(para));
	// }
	// }

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
}
