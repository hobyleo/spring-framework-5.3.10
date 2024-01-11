package com.hoby.advice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 方法抛出异常后执行
 *
 * @author hoby
 * @since 2024-01-11
 */
@Component
public class MyThrowsAdvice implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		System.out.println("方法抛出异常后执行");
	}
}
