package com.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.master.controllerAdv.CitiesException;
import com.master.service.CitiesService;

@RestController
public class CitiesController {

private static final String EXCEP_PARAM ="xyz";
private static final String ERR_CODE ="1100";
	
@Autowired	
CitiesService citiesService;

/**
 * To throw the citiesException please keep the origin and destination cities as xyz. 
 * Exception case will add the error code to the response headers to feedback the user
 */
	
@GetMapping(value="/connected", produces="text/plain" )
public String isConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination ) {	
	
	// Added this snippet to demo exception handling, if the origin and destination is "xyz", application throws a custom exception which is handled in Advice 
	if(origin.equals(EXCEP_PARAM) && destination.equals(EXCEP_PARAM)) {
		throw new CitiesException(ERR_CODE, "citiesException");
	}
	
	return citiesService.isConnected(origin, destination);
		
}

}
