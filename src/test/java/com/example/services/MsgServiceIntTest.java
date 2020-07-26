package com.example.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MsgService.class)
class MsgServiceIntTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	void testMessage() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/api/get-msgs/1234");
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("[{\"msg\":\"Hello Yechiel\"},{\"msg\":\"You have new emails\"}]", result.getResponse().getContentAsString());
	}

}
