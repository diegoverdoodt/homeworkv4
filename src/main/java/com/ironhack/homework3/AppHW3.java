package com.ironhack.homework3;

import com.ironhack.homework3.controller.CreateApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppHW3 {

	@Autowired
	private CreateApp createApp;

	public static void main(String[] args) {
		SpringApplication.run(AppHW3.class, args);
	}



}
