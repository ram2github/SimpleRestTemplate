package com.example.spring.resttemplate.demo.config;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties("quoteservice")
public class QuoteServiceConfig {

	@NotNull
	@NotEmpty
	private String url;

	@NotEmpty
	private String senderId;

	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	public String getUrl() {
		return url;
	}

	public String getSenderId() {
		return senderId;
	}
	
}
