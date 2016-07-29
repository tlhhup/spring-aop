package com.springaop.demo;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingAdvice implements MethodBeforeAdvice {

	/**
	 * @param method ��������
	 * @param args ����ֵ
	 * @param obj ���õĶ���
	 */
	@Override
	public void before(Method method, Object[] args, Object obj) throws Throwable {
		String clientName=(String) args[0];
		System.out.println("��ӭ���٣�"+clientName);
	}

}
