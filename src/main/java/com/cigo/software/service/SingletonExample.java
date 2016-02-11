package com.cigo.software.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SingletonExample {

	private static final Logger log = LoggerFactory.getLogger(SingletonExample.class);

	private Date creationDate;

	@PostConstruct
	public void initBean() {
		this.creationDate = new Date();
		log.debug(" SCOPE_SINGLETON init bean. date : " + this.creationDate);
		this.creationDate = new Date();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
