package com.intellect.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.intellect.model.User;
import com.intellect.response.IntelectResponse;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void createUser() {
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();
		user.setActive(false);
		user.setBirthDate(subtractDays(new Date(), -1));
		user.setEmail("sp@gmail.com");
		user.setfName("SandhanaPandian");
		user.setlName("R");
		user.setPinCode(636701);
		ResponseEntity<IntelectResponse> response = restTemplate.postForEntity( "http://localhost:8080/user/create", user , IntelectResponse.class );
		
			Assert.assertTrue("User Creation Failed", response.getBody().getValErrors() != null && response.getBody().getValErrors().isEmpty());
	
			user.setActive(true);
			user.setBirthDate(subtractDays(new Date(), -1));
			user.setEmail("sp@gmail.com");
			user.setfName("SandhanaPandian");
			user.setlName("R");
			user.setPinCode(636701);
			response = restTemplate.postForEntity( "http://localhost:8080/user/create", user , IntelectResponse.class );
			
				Assert.assertTrue("User Created", (response.getBody().getValErrors().isEmpty() && response.getBody().getUserId() != null));
		
	}

	@Test
	public void updateUser() {
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();
		user.setActive(false);
		user.setId(1L);
		user.setBirthDate(subtractDays(new Date(), -10));
		user.setPinCode(63672);
		ResponseEntity<IntelectResponse> response = restTemplate.postForEntity( "http://localhost:8080/user/create", user , IntelectResponse.class );
		
				Assert.assertTrue("User Updated Success", (response.getBody().getValErrors().isEmpty() && response.getBody().getUserId() != null));
		
	}

	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
	}
}
