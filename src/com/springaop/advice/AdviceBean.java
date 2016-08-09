package com.springaop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceBean {

	public void before(JoinPoint joinpoint){
		//��ȡ������ǩ��
		joinpoint.getSignature().getName();
		System.out.println("ǰ����ǿ");
	}
	
	/**
	 * ��Ϊ�������еķ��ش����з���ֵ����ǿ���ر����object���͵ķ���ֵ����ͳ��
	 */
	public Object round(ProceedingJoinPoint joinPoint){
		System.out.println("������ǿ");
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
