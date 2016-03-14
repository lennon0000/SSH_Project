package org.leadfar.egov.utils;

import org.leadfar.egov.model.PageModel;

public class PageModelContext {
	//public static PageModel pm;
	private static ThreadLocal pageThread = new ThreadLocal();
	
	public static void setPageModel (PageModel pm) {//ע�������ﱾ����ͨ������setPageModel()����ʱ���������ֵ����ʵ�������ľ�̬pm��
		//PageModelContext.pm=pm;//���������ַ���ʱ����̬����������ϵͳӦ�����ǹ��õģ����Ե�����ʹ��ʱ��������ݱ���ģ����Ծ���Ҫ����java�Դ���һ��
		//threadlocal�������ʱ�������pm��ֵ���������new�����Ķ�������������˵���ʱ���������ÿ������pm���˲���һ�������ĸ����������໥Ӱ�죻
	pageThread.set(pm);
	}
	public static PageModel getPageModel () {
		//return PageModelContext.pm;
		return (PageModel)pageThread.get();//���ǻ�÷�ʽ
	}
	public static void removePageModel(){
		pageThread.remove();
	}

	
	
}
