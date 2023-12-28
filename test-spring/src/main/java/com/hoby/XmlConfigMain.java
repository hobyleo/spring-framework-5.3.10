package com.hoby;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hoby
 * @since 2023-12-27
 */
public class XmlConfigMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		System.out.println(context.getBean("userService"));
		System.out.println(context.getBean("userService"));
		System.out.println(context.getBean("userService"));

	}
}
