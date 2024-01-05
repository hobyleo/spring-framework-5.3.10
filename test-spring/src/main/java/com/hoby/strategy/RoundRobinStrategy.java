package com.hoby.strategy;

import com.hoby.annotation.RoundRobin;
import org.springframework.stereotype.Component;

/**
 * @author hoby
 * @since 2024-01-04
 */
@Component
@RoundRobin
public class RoundRobinStrategy implements LoadBalance {

	@Override
	public String select() {
		return "roundRobin";
	}

}
