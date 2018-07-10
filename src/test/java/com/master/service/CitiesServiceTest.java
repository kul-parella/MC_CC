package com.master.service;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.master.service.CitiesService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CitiesService.class)
public class CitiesServiceTest {

@Autowired
private CitiesService citiesService;

@Test
public void contextLoads() throws Exception {
}

@Test
public void testIsConnected() {
	String resp = citiesService.isConnected("Boston", "Philadelphia");
	assertEquals("YES",resp);
}

@Test
public void testIsConnectedNO() {
	String resp = citiesService.isConnected("Philadelphia", "Albany");
	assertEquals("NO",resp);
}




}