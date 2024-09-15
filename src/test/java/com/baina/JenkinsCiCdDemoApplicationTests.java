package com.baina;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@SpringBootTest
class JenkinsCiCdDemoApplicationTests {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		
	}
	
	@Test
	void contextLoads() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		MvcResult result = mockMvc.perform(get("/naresh").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Assert.assertEquals(resultContent , "welcome to web app naresh");
	}

}
