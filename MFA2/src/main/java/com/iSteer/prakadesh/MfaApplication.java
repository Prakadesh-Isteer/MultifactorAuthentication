package com.iSteer.prakadesh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication 
@PropertySource("file:D:\\Configuration\\Application.properties")
public class MfaApplication implements CommandLineRunner {
	public static  Logger logging = LogManager.getLogger(MfaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MfaApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
	logging.info("Application is starting");
	logging.warn("While application gets Started, User should be alret for performing the operations");
		
	}
	

}
