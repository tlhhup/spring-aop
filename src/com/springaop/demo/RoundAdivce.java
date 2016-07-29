package com.springaop.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class RoundAdivce implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] arguments = invocation.getArguments();
		String clientName=(String) arguments[0];
		
		System.out.println("��ӭ"+clientName+"����");
		
		//ִ��Ԥ�ڵ��õķ���
		Object obj = invocation.proceed();
		
		System.out.println("��ӭ�´ι���");
		
		return obj;
	}

}
