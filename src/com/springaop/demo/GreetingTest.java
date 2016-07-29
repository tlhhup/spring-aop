package com.springaop.demo;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class GreetingTest {

	@Test
	public void greetTest(){
		//创建代理对象
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();

		//JDK--->面向接口
		//设置代理对象
		proxyFactory.setTarget(target);
		//设置接口
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		//设置增强
		GreetingAdvice advice=new GreetingAdvice();
		proxyFactory.addAdvice(advice);
		
		//创建代理对象
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("王五");
		
		proxy.serverTo("王五");
	}
	
	@Test
	public void greetAfterTest(){
		//创建代理对象
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();
		
		//JDK--->面向接口
		//设置代理对象
		proxyFactory.setTarget(target);
		//设置接口
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		//设置增强
		GreetAfterAdvice advice=new GreetAfterAdvice();
		proxyFactory.addAdvice(advice);
		
		//创建代理对象
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("王五");
		
		proxy.serverTo("王五");
	}
	
	@Test
	public void greetRundTest(){
		//创建代理目标对象
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();
		//强制使用CGlib创建代理对象--默认使用JDK的动态代理
		proxyFactory.setOptimize(true);
		
		//JDK--->面向接口
		//设置代理对象
		proxyFactory.setTarget(target);
		//设置接口
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		//设置增强
		RoundAdivce advice=new RoundAdivce();
		proxyFactory.addAdvice(advice);
		
		//创建代理对象
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("王五");
		
		//proxy.serverTo("王五");
	}
	
	@Test
	public void greetAdvisorTest(){
		//创建代理目标对象
		ListWaiter target=new ListWaiter();
		
		ProxyFactory proxyFactory=new ProxyFactory();
		//强制使用CGlib创建代理对象--默认使用JDK的动态代理
		proxyFactory.setOptimize(true);
		
		//JDK--->面向接口
		//设置代理对象
		proxyFactory.setTarget(target);
		//设置接口
		proxyFactory.setInterfaces(target.getClass().getInterfaces());
		
		//创建增强
		RoundAdivce advice=new RoundAdivce();
		//创建切点
		GreetStaticMethodMatcherPointcutAdvisor advisor=new GreetStaticMethodMatcherPointcutAdvisor();
		//注入增强--->创建切面
		advisor.setAdvice(advice);
		
		//设置切面
		proxyFactory.addAdvisor(advisor);
		
		//创建代理对象
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("王五");
		
		proxy.serverTo("王五");
	}
	
}
