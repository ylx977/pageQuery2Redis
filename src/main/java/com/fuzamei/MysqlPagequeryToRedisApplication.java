package com.fuzamei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.fuzamei"})
@MapperScan(basePackages = {"com.fuzamei.mapper"})
@EnableAsync
public class MysqlPagequeryToRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlPagequeryToRedisApplication.class, args);
	}
}
