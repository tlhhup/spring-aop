package com.springaop.demo;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class GreetAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object obj, Method method, Object[] args, Object arg3) throws Throwable {
		System.out.println("ָ���ķ�������"+method.getName());
		System.out.println("����ִ��֮��ִ��");
	}

}
