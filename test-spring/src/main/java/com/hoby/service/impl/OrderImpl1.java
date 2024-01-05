package com.hoby.service.impl;

import com.hoby.service.OrderInterface;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

/**
 * @author hoby
 * @since 2024-01-04
 */
@Component
@Priority(1)
public class OrderImpl1 implements OrderInterface, BeanNameAware {

	private String beanName;

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@Override
	public String toString() {
		return "OrderImpl1{" +
				"beanName='" + beanName + '\'' +
				'}';
	}
}
