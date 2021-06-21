package pl.saxatachi.kuchcik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class KuchcikApplication {
	public static void main(String[] args) {
		SpringApplication.run(KuchcikApplication.class, args);
	}


}
