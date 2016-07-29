package com.springaop.demo;

public interface Waiter {

	/**
	 * 打招呼
	 * @param name 客人
	 */
	void greetTo(String name);
	
	/**
	 * 服务
	 * @param name
	 */
	void serverTo(String name);
	
}
