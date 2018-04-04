/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-2-15
 */
public class SpringContextDB2 extends SpringBean {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		context = applicationContext;
	}

}
