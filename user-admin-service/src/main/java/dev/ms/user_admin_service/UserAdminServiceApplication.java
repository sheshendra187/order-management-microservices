package dev.ms.user_admin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "dev.ms.user_admin_service.feign")
public class UserAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAdminServiceApplication.class, args);
	}

}
