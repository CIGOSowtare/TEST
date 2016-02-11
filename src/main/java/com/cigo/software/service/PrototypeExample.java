package com.cigo.software.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class PrototypeExample {

	private static final Logger log = LoggerFactory.getLogger(PrototypeExample.class);

	private Date creationDate;

	@PostConstruct
	public void initBean() {
		this.creationDate = new Date();
		log.debug("SCOPE_PROTOTYPE init bean. date : " + this.creationDate);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
