package com.hoby.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 方法前后都可执行
 *
 * @author hoby
 * @since 2024-01-11
 */
@Component
public class MyAroundAdvice2 implements MethodInterceptor {

	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		System.out.println("方法执行Around2前");
		Object proceed = invocation.proceed();
		System.out.println("方法执行Around2后");
		return proceed;
	}
}
