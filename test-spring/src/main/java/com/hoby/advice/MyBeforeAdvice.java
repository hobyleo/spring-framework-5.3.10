package com.hoby.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 方法之前执行
 *
 * @author hoby
 * @since 2024-01-11
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("方法之前执行");
	}
}
