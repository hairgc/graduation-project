package com.github.mahui53541.graduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.github.mahui53541.graduation.mapper")
//@ComponentScan(basePackages = "com.github.mahui53541.graduation")
//@EnableAutoConfiguration
public class GraduationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationApplication.class, args);
	}
}
