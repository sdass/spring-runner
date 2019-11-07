package com.subra.springrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.subra.springrunner.others.Greeting;
import com.subra.springrunner.others.HelloService;

@RestController
public class HelloWorldController {

	Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@Autowired
	HelloService helloService;
	
	@ResponseBody
	@RequestMapping("/hi")
	public String sayHi() {
		log.info("-----sayHi()-----");
		String s = helloService.getGreeting();
		return s;
	}

	@ResponseBody
	@RequestMapping("/json")
	public Greeting sayHelo() {
		Greeting greet = new Greeting("English", "How do you do");
		log.info(greet.toString());
		return greet;

	}
	

	@ResponseBody
	//@PostMapping("/post")
	@RequestMapping(value="/post", method= RequestMethod.POST)
	public String postHelo(@RequestBody Greeting greet) {
		//Greeting greet = new Greeting("English", "How do you do");
		log.info(greet.toString());
		//return greet;
		return "received";

	}

}
