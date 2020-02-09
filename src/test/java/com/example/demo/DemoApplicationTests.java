package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	// positive test case
	
	@Test
	public void shouldReturnCorrectContent() throws Exception {
		this.mockMvc.perform(get("/newsFilter?country=us&category=business&keyword=Spectrum")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Spectrum")));
	}
	
	// Negative Test case
	
	@Test
	public void shouldReturnEmptyorNullWhenKeywordNotMatch() throws Exception {
		this.mockMvc.perform(get("/newsFilter?country=us&category=business&keyword=12346467788")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("")));
	}

}
