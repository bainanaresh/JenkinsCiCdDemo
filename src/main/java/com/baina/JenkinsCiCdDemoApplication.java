package com.baina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JenkinsCiCdDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JenkinsCiCdDemoApplication.class, args);
	}
	
	//comment addeddd
	@GetMapping("/{name}")
	public String getMessage(@PathVariable String name) {
		return "welcome to web app "+name;
	}

}
