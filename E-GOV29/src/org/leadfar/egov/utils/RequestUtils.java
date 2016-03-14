package org.leadfar.egov.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.leadfar.egov.model.Dic;

public class RequestUtils {
	
	//注册类型转换器
	
	static{
		CommonConverter cc=new CommonConverter();
		ConvertUtils.register(cc, Dic.class);
		ConvertUtils.register(cc, Date.class);
	}

	public static void copyParamsToModel(HttpServletRequest request,Object target){
		
		Map params=request.getParameterMap();
		//1.遍历所有的参数
		for (Iterator iterator = params.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			
			String property=(String)entry.getKey(); //username
			String value=((String[])entry.getValue())[0];//admin
			try {
				BeanUtils.copyProperty(target, property, value);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}
}
