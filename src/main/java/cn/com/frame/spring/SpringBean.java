/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-2-15
 */
public abstract class SpringBean implements ApplicationContextAware {

	protected static ApplicationContext context ;
	
	@Override
	public abstract void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException;
	
	/**
	 * 得到Spring上下文
	 * @return
	 */
    public static ApplicationContext getContext() {
        return context;
    }

}
