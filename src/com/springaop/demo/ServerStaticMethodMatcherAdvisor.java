package com.springaop.demo;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class ServerStaticMethodMatcherAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private static final long serialVersionUID = 1L;

	/**
	 * ����ƥ����������������ǿ�ķ���
	 */
	@Override
	public boolean matches(Method method, Class<?> clazz) {
		return "serverTo".equals(method.getName());
	}

	/**
	 * �����Ǹ���
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
