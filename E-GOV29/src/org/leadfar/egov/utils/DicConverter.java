package org.leadfar.egov.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leadfar.egov.model.Dic;
import org.leadfar.egov.service.DicService;

public class DicConverter {
	
	private static Map<String,Map<String ,Dic>> dics=new HashMap<String,Map<String,Dic>>();
	
	static{
		//³õÊ¼»¯×Öµä
		DicService dicService=JDBCHelper.getProxy((DicService)BeansFactory.getBean("dicService"));
		
		List<Dic> mainDics=dicService.search("main");
		
		for(Dic parent:mainDics){
			
			
			List<Dic> children=dicService.search(parent.getCode()); //AdmName
			Map<String,Dic> smallDics=new HashMap<String,Dic>();
			for(Dic child:children){
				
				smallDics.put(child.getCode(), child);
				
			}
			
			dics.put(parent.getCode(), smallDics);
			
		}
		
		
		
		
	}

	
	public static Dic convert(String type,String code){
		
		return dics.get(type).get(code);
	}
	
	public static Map<String,Dic> search(String type){
		return dics.get(type);
	}
	
	public static void init(){
		
	}
}
