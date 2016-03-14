package org.leadfar.egov.utils;

import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.User;

public class UserContext {
	//public static User user;
	private static ThreadLocal userThread = new ThreadLocal();
	
	public static void setUserContext (User user) {//注：在这里本来是通过调用setPageModel()方法时，将传入的值传给实例出来的静态pm，
		//PageModelContext.pm=pm;//但是用这种方法时，静态变量在整个系统应用中是公用的，所以当多人使用时会造成数据被误改，所以就需要调用java自带的一个
		//threadlocal，用这个时将传入的pm的值附到用这个new出来的对象，这个在其他人调用时，均会针对每个调用pm的人产生一个独立的副本，而不相互影响；
	userThread.set(user);
	}
	public static User getUserContext () {
		//return PageModelContext.pm;
		return (User)userThread.get();//这是获得方式
	}
	public static void removeUser(){
		userThread.remove();
	}

	
	
}
