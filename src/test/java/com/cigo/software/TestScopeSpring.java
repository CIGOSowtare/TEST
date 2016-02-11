package com.cigo.software;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cigo.software.service.ApplicationExample;
import com.cigo.software.service.PrototypeExample;
import com.cigo.software.service.SingletonExample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/cigo/software/junit-application-context.xml" })
@WebAppConfiguration
public class TestScopeSpring implements ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(TestScopeSpring.class);

	private ApplicationContext applicationContext;

	@Autowired
	private SingletonExample singleton;

	@Autowired
	private PrototypeExample prototype;

	@Autowired
	private ApplicationExample application;

	@Test
	public void scope() throws Exception {
		log.debug("##### Start test Scope spring context!");
		Thread.sleep(1000);
		
		SingletonExample se = applicationContext.getBean(SingletonExample.class);
		Assert.assertEquals(singleton.getCreationDate(), se.getCreationDate());

		PrototypeExample pe = applicationContext.getBean(PrototypeExample.class);
		Assert.assertNotEquals(prototype.getCreationDate(), pe.getCreationDate());

		ApplicationExample ae = applicationContext.getBean(ApplicationExample.class);
		Assert.assertEquals(application.getCreationDate(), ae.getCreationDate());

	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}
