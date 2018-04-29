package com.github.mahui53541.graduation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.github.mahui53541.graduation.mapper")
@ComponentScan(basePackages = "com.github.mahui53541.graduation")
@EnableAutoConfiguration
public class GraduationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationApplication.class, args);
	}
}
