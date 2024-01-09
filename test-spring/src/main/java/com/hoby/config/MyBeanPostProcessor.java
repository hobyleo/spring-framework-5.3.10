package com.hoby.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor表示bean的后置处理器
 * 可以在任意一个bean的初始化之前和初始化之后去做一些额外的自定义逻辑
 * 可以通过判断beanName进行针对性处理（某一个bean，或某一部分bean）
 *
 * @author hoby
 * @since 2023-12-26
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("userService".equals(beanName)) {
			System.out.println("UserService 初始化前");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if ("userService".equals(beanName)) {
			System.out.println("UserService 初始化后");
		}
		return bean;
	}

}
