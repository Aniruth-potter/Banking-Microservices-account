package com.bank.accountservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.bank")
@EnableJpaRepositories(basePackages = "com.bank.repos")
@EntityScan(basePackages = "com.bank.entity")
@EnableDiscoveryClient
public class AccountserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountserviceApplication.class, args);

	}

}
