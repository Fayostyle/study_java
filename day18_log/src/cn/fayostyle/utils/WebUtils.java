package cn.fayostyle.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import cn.fayostyle.entity.Admin;

public class WebUtils {
	@Deprecated
	public static <T> T copyToBean_old(HttpServletRequest request, Class<T> clazz) {
		
		try {
			T t = clazz.newInstance();
			Enumeration<String> enums = request.getParameterNames();
			while(enums.hasMoreElements()) {
				String name = enums.nextElement();
				String value = request.getParameter(name);
				BeanUtils.copyProperty(t, name, value);
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static <T> T copyToBean(HttpServletRequest request, Class<T> clazz) {
		
		try {
			T t = clazz.newInstance();
			BeanUtils.populate(t, request.getParameterMap());
			return t;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
