package com.huasoft.ilearning.util;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huasoft.ilearning.bean.Employee;

public class JsonUtil {
	/**
	 * 将Java对象转换为符合JSON格式的字符串
	 * @param obj 待转换的对象
	 * @return JSON字符�?
	 */
	public static String toJson(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		// builder.excludeFieldsWithoutExposeAnnotation();
	    //Gson gson = builder.registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter()).create();
		Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
	    return gson.toJson(obj);
	}
	
	public static String toJson3(Object obj,Type type) {
		GsonBuilder builder = new GsonBuilder();
		 builder.excludeFieldsWithoutExposeAnnotation();
	    //Gson gson = builder.registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter()).create();
		Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
	    return gson.toJson(obj,type);
	}
	
	public static String toJson3(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
	    //Gson gson = builder.registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter()).create();
		Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
	    return gson.toJson(obj,new TypeToken<Page<Employee>>() {  }.getType());
	}
	
	public static String toJson2(Object obj) {
		GsonBuilder builder = new GsonBuilder();
	    builder.excludeFieldsWithoutExposeAnnotation();
	    //Gson gson = builder.registerTypeAdapter(java.util.Date.class, new DateTypeAdapter()).create();
	    Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
	   
	    return gson.toJson(obj);
	}
	
	public static void main(String[] s){
		System.out.println(toJson2(new Date()));
	}
	
	/**
	 * 将json字符串转换为Java对象
	 * @param json
	 * @param objArray
	 * @return
	 */
	public static <T> T fromJson(String json, Type objArray) {
		GsonBuilder builder = new GsonBuilder();
	    builder.excludeFieldsWithoutExposeAnnotation();
	    Gson gson = builder.create();

	    return gson.fromJson(json, objArray);
	}
}
