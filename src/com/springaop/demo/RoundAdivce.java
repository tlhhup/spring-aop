package com.springaop.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class RoundAdivce implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] arguments = invocation.getArguments();
		String clientName=(String) arguments[0];
		
		System.out.println("欢迎"+clientName+"光临");
		
		//执行预期调用的方法
		Object obj = invocation.proceed();
		
		System.out.println("欢迎下次光临");
		
		return obj;
	}

}
