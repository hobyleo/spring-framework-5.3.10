package com.hoby.service;

import com.hoby.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hoby
 * @since 2023-12-25
 */
@Service
public class UserService {

	@Autowired
	private OrderService orderService;
	private OrderService orderService1;
	private OrderService orderService2;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 自己注入自己，Spring会注入代理对象
	 */
	@Autowired
	private UserService userService;

	@Value("hoby")
	private User user;

	/**
	 * 在有多个构造方法时，Spring默认使用无参构造方法
	 * 如果没有无参构造，若只有一个有参构造，则Spring会直接使用这个有参构造
	 * 但如果有多个有参构造，则需要使用@Autowired注解告诉Spring使用哪个构造方法
	 */
	// @Autowired
	// public UserService(OrderService orderService) {
	// 	this.orderService = orderService;
	// }

	// public UserService(OrderService orderService1, OrderService orderService2) {
	// 	this.orderService1 = orderService1;
	// 	this.orderService2 = orderService2;
	// }

	public void test() {
		System.out.println("UserService.test() start");

		System.out.println("orderService = " + orderService);
		System.out.println("user = " + user);

		System.out.println("UserService.test() end");
	}

	@Transactional
	public void insertUser() {
		jdbcTemplate.execute("insert into t_user values (null, 'hoby', '123456')");
		// 调用同类的事务方法，应使用该类的代理对象去调用，否则调用方法的事务注解会失效
		userService.innerMethod();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void innerMethod() {

	}
}
