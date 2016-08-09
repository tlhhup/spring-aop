package com.springaop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceBean {

	public void before(JoinPoint joinpoint){
		//获取方法的签名
		joinpoint.getSignature().getName();
		System.out.println("前置增强");
	}
	
	/**
	 * 因为代理类中的返回存在有返回值则增强返回必须带object类型的返回值进行统配
	 */
	public Object round(ProceedingJoinPoint joinPoint){
		System.out.println("环绕增强");
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
