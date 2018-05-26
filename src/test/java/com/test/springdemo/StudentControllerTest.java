package com.test.springdemo;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.test.springdemo.dao.StudentRepository;
import com.test.springdemo.entity.Student;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentRepository studentRepository;
	
	private Optional<Student> mockStudent;  
	
	@Test
	public void getStudentById() throws Exception {
		
		//Setup MockStudent
		Student mockStudata = new Student();
		mockStudata.setCourse("MS");
		mockStudata.setSno(999);
		mockStudata.setSname("Jhon");
		mockStudata.setFee(4567);
		mockStudent = Optional.of(mockStudata);
		
		Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(mockStudent);
		

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/v1/api/student/999").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"sno\":999,\"sname\":\"Jhon\",\"course\":\"MS\",\"fee\":4567}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void getStudentByIdWithEmpty() throws Exception {
		
		Student mockData = null;
		Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(mockData));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/v1/api/student/999").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	}

}
