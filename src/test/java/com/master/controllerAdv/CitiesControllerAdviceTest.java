package com.master.controllerAdv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.master.controllerAdv.CitiesControllerAdvice;
import com.master.controllerAdv.CitiesException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CitiesControllerAdvice.class)
public class CitiesControllerAdviceTest {
	
	@Autowired
	private CitiesControllerAdvice citiesControllerAdvice;
	
	@Test
	public void testIsConnected() {

		 ResponseEntity<HttpStatus> entity = citiesControllerAdvice.citiesExceptionMultiValue(new CitiesException("1100", "Test CitiesException"));
		 assertEquals("1100",entity.getHeaders().get("id").get(0));
		 assertEquals("Test CitiesException",entity.getHeaders().get("message").get(0));
		 assertEquals(HttpStatus.OK,entity.getStatusCode());
		
	}
	

}
