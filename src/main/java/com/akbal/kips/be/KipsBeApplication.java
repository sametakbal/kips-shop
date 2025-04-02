package com.akbal.kips.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KipsBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KipsBeApplication.class, args);
	}

}
