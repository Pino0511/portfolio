package it.jacopo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class appApplication {

	public static void main(String[] args) {
		SpringApplication.run(appApplication.class, args);
	}

}
