package fr.insa.ms.gestionNourriture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GestionFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionFoodApplication.class, args);
	}
	  @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	   }

}
