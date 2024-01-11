package com.hoby.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 方法return后执行
 *
 * @author hoby
 * @since 2024-01-11
 */
@Component
public class MyAfterReturningAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("方法return后执行");
	}
}
