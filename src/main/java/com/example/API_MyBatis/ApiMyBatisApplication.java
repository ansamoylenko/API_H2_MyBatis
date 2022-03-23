package com.example.API_MyBatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiMyBatisApplication
{

	public static void main(String[] args) {
		SpringApplication.run(ApiMyBatisApplication.class, args);
	}

}
