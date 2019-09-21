package uz.barriermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import uz.barriermanager.configs.SecurityConfig;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BarrierManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarrierManagerApplication.class, args);
	}

}
