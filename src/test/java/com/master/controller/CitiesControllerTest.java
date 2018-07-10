package com.master.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.controller.CitiesController;
import com.master.service.CitiesService;

@RunWith(MockitoJUnitRunner.class)
public class CitiesControllerTest {

	private MockMvc mockMvc;
	private final static String YES = "yes";
	private final static String NO = "no";

	@InjectMocks
	private CitiesController citiesController;

	@Mock
	CitiesService citiesService;

	@Before
	public void setup() {

		JacksonTester.initFields(this, new ObjectMapper());

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(citiesController).build();
	}

	@Test
	public void testIsConnected_YES() throws Exception {
		when(citiesService.isConnected("Boston", "Philadelphia")).thenReturn(YES);

		MvcResult resp = mockMvc.perform(get("/connected?origin=Boston&destination=Philadelphia").accept("text/plain"))
				.andExpect(status().isOk()).andReturn();

		String respString = resp.getResponse().getContentAsString();
		assertEquals(respString, YES);
	}

	@Test
	public void testIsConnected_NO() throws Exception {

		when(citiesService.isConnected("Philadelphia", "Albany")).thenReturn(NO);

		MvcResult resp = mockMvc.perform(get("/connected?origin=Philadelphia&destination=Albany").accept("text/plain"))
				.andExpect(status().isOk()).andReturn();

		String respString = resp.getResponse().getContentAsString();
		assertEquals(respString, NO);
	}

	@Test
	public void testIsConnected_YES2() throws Exception {

		when(citiesService.isConnected("xyz", "abc")).thenReturn(YES);

		mockMvc.perform(get("/connected?origin=xyz&destination=abc").accept("text/plain")).andDo(print())
				.andExpect(status().isOk());

	}

	@Test(expected = Exception.class)
	public void testIsConnected_Excep() throws Exception {

		mockMvc.perform(get("/connected?origin=xyz&destination=xyz").accept("text/plain")).andDo(print())
				.andExpect(status().isOk());

	}

}
