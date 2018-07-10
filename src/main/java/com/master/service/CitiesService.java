package com.master.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class CitiesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CitiesService.class);
	private ResourceLoader resourceLoader;
	private Map<Integer, List<String>> citiesMap = new HashMap<>();
	private static final String YES = "YES";
	private static final String NO = "NO";
	
	 @Value("${filepath}")
	    private String filepath;

	@Autowired
	public CitiesService(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@PostConstruct
	public void init() {
		try {
			LOGGER.info("CitiesService: loading cities from text file");

			Resource resource = resourceLoader.getResource(getFilepath());
			File citiesTxtFile = resource.getFile();
			BufferedReader br = new BufferedReader(new FileReader(citiesTxtFile));

			String line;
			int i = 1;
			while ((line = br.readLine()) != null) {
				List<String> lineList = Arrays.asList(line.split(","));
				citiesMap.put(i, lineList);
				i++;
			}

			br.close();

			LOGGER.info("CitiesService: loaded cities succesfully.");

		} catch (IOException | NullPointerException e) {
			LOGGER.error("File loading exception::->", e);
		}
	}

	public String isConnected(String origin, String destination) {
		
		long count =  citiesMap.values().stream().filter( list -> {
		    	if(list.contains(origin) && list.contains(destination)) {
		    		return true;
		    	}
		    	return false;
		    })
		    .count();
	
		return (count>0)? YES : NO;
	}
	
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}
