package com.example.seltest;

import com.example.seltest.jdbi.JdbiConfiguration;
import com.example.seltest.repository.ConfigRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class SelTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelTestApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;
	private Jdbi jdbi;


	@Bean
	public Jdbi getJdbi() {
		if(jdbi == null) {
			jdbi = Jdbi.create(dataSource);
			new JdbiConfiguration().configure(jdbi);
		}
		return jdbi;
	}

	@Bean
	public ConfigRepository getFieldRepository() {
		return getJdbi().onDemand(ConfigRepository.class);
	}



}
