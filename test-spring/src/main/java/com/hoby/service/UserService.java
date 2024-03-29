package com.hoby.service;

import com.hoby.annotation.RoundRobin;
import com.hoby.entity.User;
import com.hoby.mapper.OrderMapper;
import com.hoby.mapper.UserMapper;
import com.hoby.strategy.LoadBalance;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * @author hoby
 * @since 2023-12-25
 */
@Lazy
@Service
public class UserService implements UserInterface, SmartInitializingSingleton, DisposableBean {

	@Autowired
	private OrderService orderService;
	private OrderService orderService1;
	private OrderService orderService2;

	/**
	 * 如果@Autowired的类型是一个Map，且key为String类型，则Spring会注入类型为value的所有bean到这个Map中；
	 * 同理，如果@Autowired的类型是一个List，Spring会将List指定泛型类型的bean都注入到这个List中；
	 * 如果泛型是Object，则会注入Spring容器中的所有bean。
	 */
	@Autowired
	private Map<String, OrderService> orderServiceMap;

	@Autowired
	private OrderInterface orderInterface;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@RoundRobin
	private LoadBalance loadBalance;

	/**
	 * 自己注入自己，Spring会注入代理对象
	 */
	@Autowired
	private UserService userService;

	@Value("hoby")
	private User user;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrderMapper orderMapper;

	public UserService() {
	}

	/**
	 * 在有多个构造方法时，Spring默认使用无参构造方法
	 * 如果没有无参构造，若只有一个有参构造，则Spring会直接使用这个有参构造
	 * 但如果有多个有参构造，则需要使用@Autowired注解告诉Spring使用哪个构造方法
	 */
	// @Autowired
	public UserService(OrderService orderService) {
		System.out.println(1);
		this.orderService = orderService;
	}

	public UserService(OrderService orderService1, OrderService orderService2) {
		System.out.println(2);
		this.orderService1 = orderService1;
		this.orderService2 = orderService2;
	}

	@Override
	public void afterSingletonsInstantiated() {
		// 在所有非懒加载单例bean都创建完之后，会逐个调用实现了SmartInitializingSingleton接口的此方法
		// ...
		System.out.println("UserService afterSingletonsInstantiated");
	}

	@Override
	public void test() {
		System.out.println("UserService.test() start");

		// System.out.println("orderService = " + orderService);
		// System.out.println("orderServiceMap = " + orderServiceMap);
		// System.out.println("orderInterface = " + orderInterface);
		// System.out.println("loadBalance.select() = " + loadBalance.select());
		// System.out.println("user = " + user);
		// System.out.println("userMapper.selectById() = " + userMapper.selectById());
		// System.out.println("orderMapper.selectById() = " + orderMapper.selectById());

		System.out.println("UserService.test() end");
	}

	@Transactional
	public void insertUser() {
		jdbcTemplate.execute("insert into t_user values (null, 'hoby', '123456')");
		// 调用同类的事务方法，应使用该类的代理对象去调用，否则调用方法上的事务注解会失效
		userService.innerMethod();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void innerMethod() {

	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("UserService preDestroy");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("UserService destroy");
	}

	public void a() {
		System.out.println("a");
	}
}
