package org.leadfar.egov.utils;

import java.beans.Beans;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class BeansFactory {
	private static Map<String, Object> beans = new HashMap<String, Object>();
	static {
		InputStream in = BeansFactory.class.getClassLoader()
				.getResourceAsStream("beans.xml");
		// 获得输入流将配置文档读取进来
		SAXBuilder builder = new SAXBuilder();
		// 利用JDOM 来读取配置文档，需要注意的实现引入jar包
		// 利用jdom类中的SAXBuilder来读取配置文档
		Document doc = null;
		try {
			doc = builder.build(in);
			// 利用jdom类中的SAXBuilder来读取配置文档
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = doc.getRootElement();
		// 获得的是根，即beans，进行遍历，得到单个的bean
		List<Element> children = root.getChildren();
		for (Element child : children) {
			String beanId = child.getAttributeValue("id");
			String clazz = child.getAttributeValue("class");
			// 获得bean的id和class，要注意理解两个的含义。
			// 获得了名值对后，需要思考的是如何实现对接，即原来的UserDao userDao = new UserDaoImpl();
			// 即是得到了userDao 和userDaoImpl，如何对上边的userDao对接他的实现类
			// 为了不破坏serviceImpl中类的封装性，开启userDao的set方法，这样就可以通过调用setUserDao，
			// 实现其对接，setUserDao（）中传递的参数应该为new UserDaoImpl（）；
			// 关键就是如何利用userDao和userDaoImpl来实现
			Object bean = null;

			try {
				bean = Class.forName(clazz).newInstance();
				// 利用反射通过反射类的具体的地址，得到其方法
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<Element> properties = child.getChildren("property");
			for (Element prop : properties) {
				String propertyName = prop.getAttributeValue("name");
				String refBeanId = prop.getAttributeValue("ref");
				String setter = "set"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1);
				Object refBean = beans.get(refBeanId);
				try {
					Method m = bean.getClass().getMethod(setter,
							refBean.getClass().getInterfaces());
					m.invoke(bean, refBean);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			beans.put(beanId, bean);// 将方法和其ID存放在beans集合中。

		}

	}

	public static Object getBean(String beanName) {

		return beans.get(beanName);// 这里传入的beanName其实是上边设定的beanId
	}// 用这个getBean方法，传入请求的名，如userService，然后从static中加载
		// 好了的名值对中找出在beans.xml中配置的相对应的类或方法

	// public static void main(String[] args) {
	// System.out.println(BeansFactory.getBean("userService"));
	// }
	public static void init() {

	}
}