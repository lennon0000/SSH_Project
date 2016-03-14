package org.leadfar.egov.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("all")
public class SqlSessionHelper {
	public static <T> T getProxy(final T target) {
		
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
			     //根据传入的target（userServiceImpl）获取代理
			
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						Object ret = null;
						SqlSessionContext.setSessionContext(MyBatisUtils.getSession());
						try {
							
							//建立连接，通过ThreadLocal及静态对象ConnContext，
                            //将连接进行传递

							ret = method.invoke(target, args);
							//通过InvocationHandler的invoke方法利用反射机制，实现原本需要调用userServiceImpl实现的功能；
							SqlSessionContext.getSessionContext().commit();
							//对数据库进行提交操作
						} catch (Exception e) {
							SqlSessionContext.getSessionContext().rollback();//有异常时回滚，并抛出异常
							
							throw e;
						} finally {
							SqlSessionContext.getSessionContext().close();//关闭数据库连接
							SqlSessionContext.removeConnection();//移除threadLocal
						}

						return ret;
					}
				});
	}
}
