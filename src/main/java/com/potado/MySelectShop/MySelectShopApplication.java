package com.potado.MySelectShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MySelectShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySelectShopApplication.class, args);
	}

}
