package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.example.mappers") // マッパーインターフェースのパッケージを指定
public class SpringPachislotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPachislotApplication.class, args);
	}

}
