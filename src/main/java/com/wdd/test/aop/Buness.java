package com.wdd.test.aop;

import org.springframework.stereotype.Component;

@Component
public class Buness implements AspjectInter {


	@Override
	public void add() {
		// TODO Auto-generated method stub
		System.out.println("I am add method");
	}

	@Override
	public void add1() {
		// TODO Auto-generated method stub
		System.out.println("I am add1 method");
	}

	@Override
	public void textadd() {
		// TODO Auto-generated method stub
		System.out.println("I am textadd method");
	}

	@Override
	public void addTo() {
		// TODO Auto-generated method stub
		System.out.println("I am addto method");
	}

}
