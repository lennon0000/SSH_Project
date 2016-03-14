package org.leadfar.egov.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("all")
public class JDBCHelper {
	public static <T> T getProxy(final T target) {
		
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
			     //���ݴ����target��userServiceImpl����ȡ����
			
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						Object ret = null;
						try {
							ConnContext.setConnContext(DB.getConn());//�������ӣ�ͨ��ThreadLocal����̬����ConnContext��
							                                         //�����ӽ��д���

							ret = method.invoke(target, args);
							//ͨ��InvocationHandler��invoke�������÷�����ƣ�ʵ��ԭ����Ҫ����userServiceImplʵ�ֵĹ��ܣ�
							DB.commit(ConnContext.getConnContext());
							//�����ݿ�����ύ����
						} catch (Exception e) {
							DB.rollback(ConnContext.getConnContext());//���쳣ʱ�ع������׳��쳣
							throw e;
						} finally {
							DB.close(ConnContext.getConnContext());//�ر����ݿ�����
							ConnContext.removeConnection();//�Ƴ�threadLocal
						}

						return ret;
					}
				});
	}
}
