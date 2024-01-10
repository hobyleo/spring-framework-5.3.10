package com.hoby;

import com.hoby.service.UserInterface;
import com.hoby.service.UserService;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
		proxyFactory.addAdvice(new org.aopalliance.intercept.MethodInterceptor() {
			@Nullable
			@Override
			public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
				System.out.println("before");
				Object result = invocation.proceed();
				System.out.println("after");
				return result;
			}
		});

		UserInterface userService = (UserInterface) proxyFactory.getProxy();
		userService.test();
	}

}
