package com.hoby;

import com.hoby.advice.MyBeforeAdvice;
import com.hoby.service.UserInterface;
import com.hoby.service.UserService;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hoby
 * @since 2024-01-10
 */
public class AopTest {

	public static void main(String[] args) {
		// testJdkProxy();
		// testCglib();
		testSpringProxy();
	}

	private static void testJdkProxy() {
		UserService target = new UserService();

		Object proxy = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserInterface.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before");
				Object result = method.invoke(target, args);
				System.out.println("after");
				return result;
			}
		});

		UserInterface userInterface = (UserInterface) proxy;
		userInterface.test();
	}

	private static void testCglib() {
		UserService target = new UserService();

		// 通过cglib技术
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserService.class);

		// 定义额外逻辑，也就是代理逻辑
		enhancer.setCallbacks(new Callback[]{new MethodInterceptor() {
			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				System.out.println("before");
				Object result = methodProxy.invoke(target, objects);
				System.out.println("after");
				return result;
			}
		}});

		// 动态代理所创建出来的UserService对象
		UserService userService = (UserService) enhancer.create();

		// 执行代理方法
		userService.test();
	}

	private static void testSpringProxy() {
		UserService target = new UserService();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);
		// proxyFactory.addAdvice(new MyAroundAdvice1());
		// proxyFactory.addAdvice(new MyAroundAdvice2());

		// advisor = pointcut + advice
		proxyFactory.addAdvisor(new PointcutAdvisor() {
			@Override
			public Pointcut getPointcut() {
				return new StaticMethodMatcherPointcut() {
					@Override
					public boolean matches(Method method, Class<?> targetClass) {
						return method.getName().equals("test");
					}
				};
			}

			@Override
			public Advice getAdvice() {
				return new MyBeforeAdvice();
			}

			@Override
			public boolean isPerInstance() {
				return false;
			}
		});

		/*
		 * 代理对象执行过程：
		 * 1. 在使用ProxyFactory创建代理对象之前，需要往ProxyFactory先添加Advisor
		 * 2. 代理对象在执行某个方法时，会把ProxyFactory中的Advisor拿出来和当前正在执行的方法进行匹配筛选
		 * 3. 把和方法所匹配的Advisor适配成MethodInterceptor
		 * 4. 把和当前方法匹配的MethodInterceptor链，以及被代理对象、代理对象、代理类、当前Method对象、方法参数封装为MethodInvocation对象
		 * 5. 调用MethodInvocation的proceed()方法，开始执行各个MethodInterceptor以及被代理对象的对应方法
		 * 6. 按顺序调用每个MethodInterceptor的invoke()方法，并且会把MethodInvocation对象传入invoke()方法
		 * 7. 直到执行完最后一个MethodInterceptor了，就会调用invokeJoinpoint()方法，从而执行被代理对象的当前方法
		 */
		UserService userService = (UserService) proxyFactory.getProxy();
		// userService.test();
		userService.a();
	}

}
