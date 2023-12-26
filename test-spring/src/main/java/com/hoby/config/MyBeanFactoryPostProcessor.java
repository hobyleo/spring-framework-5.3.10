package com.hoby.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * BeanFactoryPostProcessor表示bean工厂的后置处理器
 * 和BeanPostProcessor类似，其用于干涉BeanFactory的创建过程
 *
 * @author hoby
 * @since 2023-12-26
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor: beanFactory 自定义处理...");
	}

}
