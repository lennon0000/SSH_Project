package org.leadfar.egov.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	private static SqlSessionFactory factory ;
	static{
		Reader reader=null;
		
		try {
			reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		factory = builder.build(reader);
	}
	public static SqlSession  getSession(){
		return factory.openSession();
	}
}
