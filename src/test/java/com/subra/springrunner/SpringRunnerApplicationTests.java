package com.subra.springrunner;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.subra.springrunner.others.HelloRepo;
import com.subra.springrunner.others.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRunnerApplicationTests {
	private static Logger log = LoggerFactory
			.getLogger(SpringRunnerApplication.class);

	private MockMvc mockMvc; // 1

	@Test
	public void contextLoads() {
		log.info("spring testing begins");
	}

	@InjectMocks
	// 3
	HelloWorldController hController;

	@Mock
	// 2
	HelloService tservice;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(hController).build(); // 4
	}

	@Test
	public void hiTestMethod() throws Exception {

		log.info("---------1----------");
		try {
			Mockito.when(tservice.getGreeting()).thenReturn("Hi and Hello"); // 5

			log.info("mockvc is null=" + (mockMvc == null));
			mockMvc.perform(MockMvcRequestBuilders.get("/hi"))
					// 6
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(
							MockMvcResultMatchers.content().string(
									"Hi and Hello"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		Mockito.verify(tservice).getGreeting(); // 7

	}

	@Test
	public void jsonTestMethod() throws Exception {
		log.info("---------2----------");
		log.info("mockvc is null=" + (mockMvc == null));
		mockMvc.perform(
				MockMvcRequestBuilders.get("/json").accept(
						MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.lang",
								Matchers.is("English")))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.value",
								Matchers.is("How do you do")));

	}

	@Test
	public void jsonTestPostMethod() throws Exception {
		log.info("---------3----------");
		log.info("mockvc is null=" + (mockMvc == null));
		String jsondata = "{  \"lang\": \"Chinese\",\r\n  \"greet\": \"Minhao\"\r\n}";
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/post")
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsondata))

					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(
							MockMvcResultMatchers.content().string("received"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
