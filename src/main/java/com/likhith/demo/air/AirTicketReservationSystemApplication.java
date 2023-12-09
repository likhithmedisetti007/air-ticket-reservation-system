package com.likhith.demo.air;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class AirTicketReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirTicketReservationSystemApplication.class, args);
	}

}