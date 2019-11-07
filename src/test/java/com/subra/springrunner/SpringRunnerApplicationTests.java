package com.subra.springrunner;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRunnerApplicationTests {
	private static Logger log = LoggerFactory.getLogger(SpringRunnerApplication.class);	
	
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
		log.info("spring testing begins");
	}
	
	@InjectMocks
	HelloWorldController hController;

	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.standaloneSetup(hController).build();
	}
	
	@Test
	public void hiTestMethod() throws Exception{
		
		log.info("---------1----------");
		
		log.info( "mockvc is null=" + (mockMvc == null));
		mockMvc.perform(MockMvcRequestBuilders.get("/hi"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Hi"));

		}
	
	@Test
	public void jsonTestMethod() throws Exception{
		log.info("---------2----------");
		log.info( "mockvc is null=" + (mockMvc == null));
		mockMvc.perform(MockMvcRequestBuilders.get("/json").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.lang", Matchers.is("English")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("How do you do")))	;


		}	
	
}
