package com.cigo.software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.cigo.software.service.PrototypeExample;
import com.cigo.software.service.SingletonExample;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class HelloWorldBean {

	@Autowired
	private SingletonExample singleton;

	@Autowired
	private PrototypeExample prototype;

	public String getTitle() {
		return "Hello world!";
	}

}
