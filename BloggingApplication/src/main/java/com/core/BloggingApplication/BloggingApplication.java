package com.core.BloggingApplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}
	@Bean
	public ModelMapper modelmapper()//add dependencey modelmapper 
	//make(spring automatic injects) the object of this class and use this method to map the models. 
	{
		return new ModelMapper();
	}

}
