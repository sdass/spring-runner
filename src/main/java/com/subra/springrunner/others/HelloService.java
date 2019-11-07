package com.subra.springrunner.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subra.springrunner.HelloWorldController;

@Service
public class HelloService {
	Logger log = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	HelloRepo helloRepo;
	
	public String getGreeting(){
		
		log.info("HelloService: getGreeting()...");
		return helloRepo.getHi();
		// return "hi"; ok
	}

	

}
