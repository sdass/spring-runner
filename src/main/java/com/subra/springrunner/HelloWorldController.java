package com.subra.springrunner;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloWorldController {
	
	Logger log = LoggerFactory.getLogger(HelloWorldController.class);
	
	@ResponseBody
	@RequestMapping("/hi")
	public String sayHi(){
		return "Hi";
	}
	
	@ResponseBody
	@RequestMapping("/json")
	public Greeting sayHelo(){
		Greeting greet =  new Greeting("English", "How do you do");
		log.info(greet.toString());
		return greet;
		
	}

	
	@Getter @Setter @ToString @AllArgsConstructor 
	private class Greeting {
		
		 String lang;
		 @NonNull String  value;		
	}

}


