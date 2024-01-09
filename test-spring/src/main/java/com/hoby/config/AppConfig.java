package com.hoby.config;

import com.hoby.entity.User;
import com.hoby.service.OrderService;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.beans.PropertyEditor;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author hoby
 * @since 2023-12-25
 */
@ComponentScan("com.hoby")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class AppConfig {

	/**
	 * 注册Spring提供的类型转化工具，优先级比PropertyEditor低
	 */
	@Bean
	public ConversionServiceFactoryBean conversionService() {

		ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
		factoryBean.setConverters(Collections.singleton(new StringToUserConverter()));

		return factoryBean;
	}

	/**
	 * 注册JDK提供的PropertyEditor，优先级比Spring提供的高
	 */
	@Bean
	public CustomEditorConfigurer customEditorConfigurer() {
		CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();

		HashMap<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();
		customEditors.put(User.class, StringToUserPropertyEditor.class);

		customEditorConfigurer.setCustomEditors(customEditors);
		return customEditorConfigurer;
	}

	@Bean
	public ApplicationListener applicationListener() {
		return event -> System.out.println("接收到了一个事件: " + event);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/**
	 * 相同类型及名字的bean只会注册一次（比如类上已经有@Component注解，又通过@Bean注册了，此时只会存在一个bean）
	 */
	@Bean
	public OrderService orderService() {
		return new OrderService();
	}

	@Bean
	public OrderService orderService1() {
		return new OrderService();
	}

	@Bean
	public OrderService orderService2() {
		return new OrderService();
	}

}
