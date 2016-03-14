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
		// ����������������ĵ���ȡ����
		SAXBuilder builder = new SAXBuilder();
		// ����JDOM ����ȡ�����ĵ�����Ҫע���ʵ������jar��
		// ����jdom���е�SAXBuilder����ȡ�����ĵ�
		Document doc = null;
		try {
			doc = builder.build(in);
			// ����jdom���е�SAXBuilder����ȡ�����ĵ�
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = doc.getRootElement();
		// ��õ��Ǹ�����beans�����б������õ�������bean
		List<Element> children = root.getChildren();
		for (Element child : children) {
			String beanId = child.getAttributeValue("id");
			String clazz = child.getAttributeValue("class");
			// ���bean��id��class��Ҫע����������ĺ��塣
			// �������ֵ�Ժ���Ҫ˼���������ʵ�ֶԽӣ���ԭ����UserDao userDao = new UserDaoImpl();
			// ���ǵõ���userDao ��userDaoImpl����ζ��ϱߵ�userDao�Խ�����ʵ����
			// Ϊ�˲��ƻ�serviceImpl����ķ�װ�ԣ�����userDao��set�����������Ϳ���ͨ������setUserDao��
			// ʵ����Խӣ�setUserDao�����д��ݵĲ���Ӧ��Ϊnew UserDaoImpl������
			// �ؼ������������userDao��userDaoImpl��ʵ��
			Object bean = null;

			try {
				bean = Class.forName(clazz).newInstance();
				// ���÷���ͨ��������ľ���ĵ�ַ���õ��䷽��
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
			beans.put(beanId, bean);// ����������ID�����beans�����С�

		}

	}

	public static Object getBean(String beanName) {

		return beans.get(beanName);// ���ﴫ���beanName��ʵ���ϱ��趨��beanId
	}// �����getBean���������������������userService��Ȼ���static�м���
		// ���˵���ֵ�����ҳ���beans.xml�����õ����Ӧ����򷽷�

	// public static void main(String[] args) {
	// System.out.println(BeansFactory.getBean("userService"));
	// }
	public static void init() {

	}
}