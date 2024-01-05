package com.hoby.strategy;

import com.hoby.annotation.Random;
import org.springframework.stereotype.Component;

/**
 * @author hoby
 * @since 2024-01-04
 */
@Component
@Random
public class RandomStrategy implements LoadBalance {

	@Override
	public String select() {
		return "random";
	}

}
