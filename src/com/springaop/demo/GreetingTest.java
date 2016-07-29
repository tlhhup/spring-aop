package com.springaop.demo;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class GreetingTest {

	@Test
	public void greetTest(){
		//�����������
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();

		//JDK--->����ӿ�
		//���ô������
		proxyFactory.setTarget(target);
		//���ýӿ�
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		//������ǿ
		GreetingAdvice advice=new GreetingAdvice();
		proxyFactory.addAdvice(advice);
		
		//�����������
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("����");
		
		proxy.serverTo("����");
	}
	
	@Test
	public void greetAfterTest(){
		//�����������
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();
		
		//JDK--->����ӿ�
		//���ô������
		proxyFactory.setTarget(target);
		//���ýӿ�
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		//������ǿ
		GreetAfterAdvice advice=new GreetAfterAdvice();
		proxyFactory.addAdvice(advice);
		
		//�����������
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("����");
		
		proxy.serverTo("����");
	}
	
	@Test
	public void greetRundTest(){
		//��������Ŀ�����
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();
		//ǿ��ʹ��CGlib�����������--Ĭ��ʹ��JDK�Ķ�̬����
		proxyFactory.setOptimize(true);
		
		//JDK--->����ӿ�
		//���ô������
		proxyFactory.setTarget(target);
		//���ýӿ�
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		//������ǿ
		RoundAdivce advice=new RoundAdivce();
		proxyFactory.addAdvice(advice);
		
		//�����������
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("����");
		
		//proxy.serverTo("����");
	}
	
	@Test
	public void greetAdvisorTest(){
		//��������Ŀ�����
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();
		//ǿ��ʹ��CGlib�����������--Ĭ��ʹ��JDK�Ķ�̬����
		proxyFactory.setOptimize(true);
		
		//JDK--->����ӿ�
		//���ô������
		proxyFactory.setTarget(target);
		//���ýӿ�
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		
		//������ǿ
		RoundAdivce advice=new RoundAdivce();
		//�����е�
		GreetStaticMethodMatcherPointcutAdvisor advisor=new GreetStaticMethodMatcherPointcutAdvisor();
		//ע����ǿ--->��������
		advisor.setAdvice(advice);
		
		//��������
		proxyFactory.addAdvisor(advisor);
		
		//�����������
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("����");
		
		proxy.serverTo("����");
	}
	
}
