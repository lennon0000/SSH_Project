package org.leadfar.egov.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("all")
public class SqlSessionHelper {
	public static <T> T getProxy(final T target) {
		
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
			     //���ݴ����target��userServiceImpl����ȡ����
			
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						Object ret = null;
						SqlSessionContext.setSessionContext(MyBatisUtils.getSession());
						try {
							
							//�������ӣ�ͨ��ThreadLocal����̬����ConnContext��
                            //�����ӽ��д���

							ret = method.invoke(target, args);
							//ͨ��InvocationHandler��invoke�������÷�����ƣ�ʵ��ԭ����Ҫ����userServiceImplʵ�ֵĹ��ܣ�
							SqlSessionContext.getSessionContext().commit();
							//�����ݿ�����ύ����
						} catch (Exception e) {
							SqlSessionContext.getSessionContext().rollback();//���쳣ʱ�ع������׳��쳣
							
							throw e;
						} finally {
							SqlSessionContext.getSessionContext().close();//�ر����ݿ�����
							SqlSessionContext.removeConnection();//�Ƴ�threadLocal
						}

						return ret;
					}
				});
	}
}
