package com.hoby;

import com.hoby.config.AppConfig;
import com.hoby.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hoby
 * @since 2023-12-25
 */
public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		UserService userService = (UserService) context.getBean("userService");
		userService.test();
		// userService.insertUser();


	}
}
