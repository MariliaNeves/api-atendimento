package com.atendimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


@SpringBootApplication
@EntityScan (basePackages = "com.atendimento.model.entity")
@EnableJpaRepositories(basePackages = "com.atendimento.model.repository")

public class AtendimentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtendimentoApplication.class, args);
	}

}
