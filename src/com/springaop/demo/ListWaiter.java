package com.springaop.demo;

public class ListWaiter implements Waiter {

	@Override
	public void greetTo(String name) {
		System.out.println(this.getClass().getSimpleName()+"--->greetTo--->"+name);
	}

	@Override
	public void serverTo(String name) {
		System.out.println(this.getClass().getSimpleName()+"--->serverTo--->"+name);
	}

}
