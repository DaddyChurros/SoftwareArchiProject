package fr.insa.ms.occupationTable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OccupationTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(OccupationTableApplication.class, args);
	}

}