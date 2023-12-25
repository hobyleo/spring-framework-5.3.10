package com.hoby.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hoby
 * @since 2023-12-25
 */
@Aspect
@Component
public class UserServiceAspect {

	@Pointcut("execution(public void com.hoby.service.UserService.test())")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {
		System.out.println("UserService.test() before");
	}
}
