package com.springaop.demo;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class GreetStaticMethodMatcherPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private static final long serialVersionUID = 1L;

	/**
	 * ���ھ����е�ķ���
	 */
	@Override
	public boolean matches(Method method, Class<?> clazz) {
		return "greetTo".equals(method.getName());
	}
	
	/**
	 * ���ھ�����
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
