package br.com.alex.frasesmusicais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FrasesmusicaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrasesmusicaisApplication.class, args);
	}

}
