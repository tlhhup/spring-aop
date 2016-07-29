package com.springaop.demo;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class GreetStaticMethodMatcherPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private static final long serialVersionUID = 1L;

	/**
	 * 用于决定切点的方法
	 */
	@Override
	public boolean matches(Method method, Class<?> clazz) {
		return "greetTo".equals(method.getName());
	}
	
	/**
	 * 用于决定类
	 */
	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter() {
			
			@Override
			public boolean matches(Class<?> clazz) {
				return Waiter.class.isAssignableFrom(clazz);
			}
		};
	}
	
}
