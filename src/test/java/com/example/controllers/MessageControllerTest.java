package com.example.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
class MessageControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	void testGetMessages() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/api/get-msgs/3/email");
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("[{\"msg\":\"You have new email\"}]",
				result.getResponse().getContentAsString());
	}

	@Test
	void testCreateAccount() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/api/account")
				.content("{\"name\":\"Avi\"}").contentType(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testCreateMessage() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/api/message/5/chat")
				.content("{\"msg\":\"You have new chat message\"}").contentType(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testGetAccounts() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/api/getaccounts");
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("[{\"id\":1,\"name\":\"Yechiel\"},"
				+"{\"id\":2,\"name\":\"Chaim\"},"
				+"{\"id\":3,\"name\":\"Yossi\"},"
				+"{\"id\":4,\"name\":\"Yonatan\"},"
				+"{\"id\":5,\"name\":\"Avi\"}]"
				,result.getResponse().getContentAsString());
	}

}
