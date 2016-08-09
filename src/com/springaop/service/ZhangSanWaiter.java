package com.springaop.service;

public class ZhangSanWaiter implements Waiter {

	@Override
	public void greeTo(String name) {
		System.out.println(this.getClass().getSimpleName()+" greeTo-->"+name);
	}

	@Override
	public void serverTo(String name) {
		System.out.println(this.getClass().getSimpleName()+" serverTo-->"+name);
	}

	@Override
	public int count() {
		return 0;
	}

}
