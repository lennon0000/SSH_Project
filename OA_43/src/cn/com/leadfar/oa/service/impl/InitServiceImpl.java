package cn.com.leadfar.oa.service.impl;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import cn.com.leadfar.oa.dao.InitDao;
import cn.com.leadfar.oa.service.InitService;
import cn.com.leadfar.oa.service.ResourceService;

public class InitServiceImpl implements InitService, BeanFactoryAware {

	private String path;

	private BeanFactory factory;

	@Resource
	private InitDao initDao;
	
	/**
	 * Spring在实例化InitServiceImpl对象的时候，如果InitServiceImpl类实现了
	 * BeanFactoryAware接口，Spring会自动把自身（BeanFactory）注入到此对象
	 */
	@Override
	public void setBeanFactory(BeanFactory factory) throws BeansException {
		this.factory = factory;
	}

	@Override
	public void addInitDatas() {
		try {
			// 从类路径的根目录中读取指定的包含初始化数据的XML文件
			Document document = new SAXReader().read(Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(path));

			// document.selectNodes("//entity");

			// 文档根元素
			Element root = document.getRootElement();

			// 从文档根元素中提取出实体类所在的包名
			String pkg = root.attributeValue("package");

			// 得到根元素下面包含的所有的entity元素
			List entityElements = root.elements("entity");
			for (Iterator iterator = entityElements.iterator(); iterator
					.hasNext();) {
				Element entityElement = (Element) iterator.next();
				addEntity(pkg, entityElement, null, null);
			}
			//重建所有的ActionResource资源
			ResourceService resourceService = (ResourceService)factory.getBean("resourceService");
			resourceService.rebuildActionResources();
			
			initDao.addInitAdmin();//初始化user
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void addEntity(String pkg, Element entityElement,
			Object parentEntity, String callString) {
		try {

			// 根据包名和类名，得到全路径类名
			String clz = pkg + "." + entityElement.attributeValue("class");

			// 根据全路径类名，创建实体对象
			Object entity = Class.forName(clz).newInstance();

			// 给entity对象赋值
			// 即提取出当前Element中的所有属性，并用反射机制给entity对象赋值
			Iterator iterator = entityElement.attributeIterator();
			while (iterator.hasNext()) {
				Attribute attribute = (Attribute) iterator.next();
				String propertyName = attribute.getName();
				if (!propertyName.equals("class")
						&& !propertyName.equals("call")) {
					String propertyValue = attribute.getValue();
					// 给entity相应的属性赋值，这里使用的是apache-commons-beanutils工具包，所以需要加入这个依赖包
					BeanUtils.copyProperty(entity, propertyName, propertyValue);
				}
			}

			// 判断parentEntity是否为空，如果不是空，则给parent对象赋值
			// BeanUtils.copyProperty(entity, "parent", parentEntity);
			if (parentEntity != null) {
				Method[] ms = entity.getClass().getMethods();
				for (Method m : ms) {
					if (m.getName().equals("setParent")) {
						m.invoke(entity, parentEntity);
					}
				}
			}

			// 要调用哪个服务对象的哪个方法
			String call = entityElement.attributeValue("call");
			if (call != null) {
				callString = call;
			}

			if (callString == null) {
				throw new RuntimeException(
						"没有找到call属性，无法获知要调用哪个服务对象的哪个方法！请配置call属性！");
			}

			// 得到服务对象的ID
			String serviceId = callString.substring(0, callString.indexOf("."));
			// 得到要调用的方法名称
			String methodName = callString
					.substring(callString.indexOf(".") + 1);

			// 通过BeanFactory得到服务对象
			Object service = factory.getBean(serviceId);

			// 得到service对象的所有方法
			Method[] ms = service.getClass().getMethods();
			for (Method m : ms) {
				if (m.getName().equals(methodName)) {
					// 调用其中我们想要调用的方法
					m.invoke(service, entity);
				}
			}

			// 判断当前entity下面是否还有其他的entity元素
			List subEntityElements = entityElement.elements("entity");
			for (Iterator iterator2 = subEntityElements.iterator(); iterator2
					.hasNext();) {
				Element e = (Element) iterator2.next();
				// 递归插入本entity元素下面的其它entity对象
				addEntity(pkg, e, entity, callString);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

}
