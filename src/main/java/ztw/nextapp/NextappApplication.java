package ztw.nextapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NextappApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextappApplication.class, args);
	}

}
