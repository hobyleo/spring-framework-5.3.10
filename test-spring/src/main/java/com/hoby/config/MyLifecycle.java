package com.hoby.config;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * 实现Lifecycle接口后，Spring在容器启动或关闭时会执行相应方法
 *
 * @author hoby
 * @since 2024-01-08
 */
@Component
public class MyLifecycle implements SmartLifecycle {

	private boolean isRunning;

	@Override
	public void start() {
		this.isRunning = true;
		System.out.println("容器启动");
	}

	@Override
	public void stop() {
		this.isRunning = false;
		System.out.println("容器关闭");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

}
