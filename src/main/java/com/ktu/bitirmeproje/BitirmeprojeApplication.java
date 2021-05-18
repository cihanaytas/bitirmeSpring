package com.ktu.bitirmeproje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableElasticsearchRepositories("com.ktu.bitirmeproje.data.repository")
public class BitirmeprojeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitirmeprojeApplication.class, args);
	}

}
