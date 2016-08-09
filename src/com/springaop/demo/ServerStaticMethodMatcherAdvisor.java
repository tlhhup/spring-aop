package com.springaop.demo;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class ServerStaticMethodMatcherAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private static final long serialVersionUID = 1L;

	/**
	 * 方法匹配器：决定引入增强的方法
	 */
	@Override
	public boolean matches(Method method, Class<?> clazz) {
		return "serverTo".equals(method.getName());
	}

	/**
	 * 决定那个类
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
