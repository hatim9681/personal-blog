package com.blogpersonnel.blogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BlogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(5000);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}
}
