package com.master.controllerAdv;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CitiesControllerAdvice {

	// Application level CitiesException handling

	@ExceptionHandler(CitiesException.class)
	public ResponseEntity<HttpStatus> citiesExceptionMultiValue(CitiesException citiesExcep) {

		// error code and error message added to the headers

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("id", citiesExcep.getId());
		map.add("message", citiesExcep.getMessage());
		return new ResponseEntity<HttpStatus>(map, HttpStatus.OK);
	}

}
