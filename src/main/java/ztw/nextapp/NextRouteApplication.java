package ztw.nextapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class NextRouteApplication {

	@GetMapping("/")
	public String echoTheUsersEmailAddress(Principal principal) {
		return "Hey there! Your email address is: ";
	}

	@GetMapping("/login")
	public String log(Principal principal) {
		return "Logowanie";
	}

	@GetMapping("/cos")
	public String cos(Principal principal) {
		return "C";
	}

	public static void main(String[] args) {
		SpringApplication.run(NextRouteApplication.class, args);
	}

}
