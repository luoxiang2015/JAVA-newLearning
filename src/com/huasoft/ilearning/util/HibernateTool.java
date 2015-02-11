package com.huasoft.ilearning.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTool {
	
	private static SessionFactory sessionFactory;
	
	static{
		//读取配置
		Configuration config=new Configuration().configure();
		
		try {
			sessionFactory=config.buildSessionFactory();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获得sessionFacroy对象
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
