package com.hoby.config;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author hoby
 * @since 2024-01-08
 */
@Component
public class MyLifecycle implements SmartLifecycle {

	private boolean isRunning;

	@Override
	public void start() {
		this.isRunning = true;
		System.out.println("容器启动...");
	}

	@Override
	public void stop() {
		this.isRunning = false;
		System.out.println("容器关闭...");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

}
