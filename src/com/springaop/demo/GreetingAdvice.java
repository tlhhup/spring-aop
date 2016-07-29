package com.springaop.demo;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingAdvice implements MethodBeforeAdvice {

	/**
	 * @param method 方法名称
	 * @param args 参数值
	 * @param obj 调用的对象
	 */
	@Override
	public void before(Method method, Object[] args, Object obj) throws Throwable {
		String clientName=(String) args[0];
		System.out.println("欢迎光临："+clientName);
	}

}
