package com.ktu.bitirmeproje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class BitirmeprojeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitirmeprojeApplication.class, args);
	}

}
