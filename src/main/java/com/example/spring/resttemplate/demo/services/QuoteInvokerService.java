package com.example.spring.resttemplate.demo.services;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.spring.resttemplate.demo.config.QuoteServiceConfig;
import com.example.spring.resttemplate.demo.resources.Quote;


@Controller
@RestController
@RequestMapping(value="/quote")
public class QuoteInvokerService {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder; // this is injected by spring boot framework itself
	
	@Autowired
	private QuoteServiceConfig qsConfig;
	
	/**
	 * Create a singleton restTemplate to be used by the application
	 * No one calls this 
	 * @param restTemplateBuilder - is injected by Spring using the @Autowired logic
	 * @return RestTemplate
	 */
	@Bean 
	public RestTemplate restTemplate(){
		logger.info("restTemplate method called on startup,rt ? Please check the logs !!!" + restTemplateBuilder);
		logger.info("Sender : "+ qsConfig.getSenderId() + " URL :" + qsConfig.getUrl());
		return restTemplateBuilder.build();
	}
	
	/***
	 * This method is invoked when http://localhost:8080/quote/ is invoked
	 * @param restTemplate - restTemplate is a singleton bean autowired and injected
	 * @return Quote - the random qupote from external server with metadata
	 */
	@RequestMapping(value="/",method = RequestMethod.GET)
	public @ResponseBody Quote getRandomQuote(RestTemplate restTemplate){
	
	   logger.info("NOTE : restTemplate is a singleton bean autowired and injected !!!!!" + restTemplate);	
	   // value retrieved from external MS
	   Quote springbootquote = restTemplate.getForObject(qsConfig.getUrl(), Quote.class);
	   logger.info("Retrieved from Quotation server" + springbootquote.toString());
	   
	   // add rest of the data
	   springbootquote.setSenderId(qsConfig.getSenderId());
	   springbootquote.setTimestamp(LocalDateTime.now().toString());
	   
	   logger.info("Final response to be sent back:" + springbootquote.toString());
	 
	   return springbootquote;
		
	}
}
