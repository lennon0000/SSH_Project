package org.leadfar.egov.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.leadfar.egov.model.Dic;

public class CommonConverter implements Converter {

	public Object convert(Class clazz, Object value) {
		
		if(clazz==Dic.class){
			Dic dic=new Dic();
			dic.setCode((String)value);
			return dic;
		}
		if(clazz==Date.class){
			
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse((String)value);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
