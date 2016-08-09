package com.springaop.Test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springaop.service.Waiter;

public class AdviceTest {

	@Test
	public void beforeAdvice(){
		ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
		Waiter zhangSan = (Waiter) act.getBean("com.springaop.service.ZhangSanWaiter");
		Waiter lisi = (Waiter) act.getBean("com.springaop.service.LisiWaiter");
		zhangSan.greeTo("»ðºü");
		lisi.serverTo("ÄãÃÃ");
	}
	
	@Test
	public void aroundAdvice(){
		ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
		Waiter zhangSan = (Waiter) act.getBean("com.springaop.service.ZhangSanWaiter");
		Waiter lisi = (Waiter) act.getBean("com.springaop.service.LisiWaiter");
		System.out.println(zhangSan.count());
		System.out.println(lisi.count());
	}
	
}
