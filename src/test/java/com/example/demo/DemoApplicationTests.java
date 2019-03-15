package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
	
	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		
		String welcome = restTemplate.getForObject("/", String.class);
		assertThat(welcome).isEqualTo("welcome");
		
		String items = restTemplate.getForObject("/items", String.class);
		assertThat(items).isEqualTo("{\"data\":\"[(id:1,name:abc), (id:2,name:def), (id:3,name:ghi), (id:4,name:jkl)]\"}");
		
		String evict = restTemplate.getForObject("/evict", String.class);
		assertThat(evict).isEqualTo("evicted.");
	}

}
