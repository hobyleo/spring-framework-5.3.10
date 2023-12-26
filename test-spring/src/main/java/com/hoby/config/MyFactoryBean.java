package com.hoby.config;

import com.hoby.service.UserService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 如果想要一个bean完全由我们自己创造，则使用FactoryBean
 * 通过这种方式创建的bean，只会经过bean生命周期的初始化后阶段，其他的生命周期阶段不会经过，但FactoryBean本身会经过完整的生命周期
 * FactoryBean的名字默认为首字母小写，通过此名字获取到的是getObject()返回的对象
 * 如果要获取FactoryBean对象本身，则在beanName前面加上&符号
 *
 * @author hoby
 * @since 2023-12-26
 */
@Component
// @Component("userService")
public class MyFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		UserService userService = new UserService();
		return userService;
	}

	@Override
	public Class<?> getObjectType() {
		return UserService.class;
	}

	@Override
	public boolean isSingleton() {
		// 默认是单例
		return true;
	}
}
