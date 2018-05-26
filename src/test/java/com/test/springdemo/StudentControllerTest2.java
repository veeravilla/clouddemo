package com.test.springdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.test.springdemo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class StudentControllerTest2 {
	
	private String baseUrl = "http://localhost:8080";

    private String endpointToThrowException = "/v1/api/student/999";
    
    @Autowired
    private TestRestTemplate testRestTemplate;
	
	@Test(expected=ResourceNotFoundException.class)
	public void getStudentByIdWithEmpty() throws Exception {
		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
	    request.set("username", "admin");
	    request.set("password", "admin");
	    
		testRestTemplate.withBasicAuth("admin", "admin").getForObject(baseUrl + endpointToThrowException, Student.class);
	}

}
