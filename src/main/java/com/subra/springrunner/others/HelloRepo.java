package com.subra.springrunner.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

import com.subra.springrunner.HelloWorldController;


//@Repository Dummy
@Component
public class HelloRepo {

	Logger log = LoggerFactory.getLogger(HelloWorldController.class);
	public String getHi(){
		log.info("HelloRepo: getHi()...");
		return "hi";
	}
}
