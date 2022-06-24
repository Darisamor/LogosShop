package com.darisamor.logosShop;

import com.darisamor.logosShop.dto.TestDTO;
import com.darisamor.logosShop.entity.TestEntity;
import com.darisamor.logosShop.service.TestService;
import com.darisamor.logosShop.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogosShopApplication implements CommandLineRunner {

	@Autowired
	private TestService testService;

	@Autowired
	private ModelMapperUtil modelMapperUtil;

	public static void main(String[] args) {
		SpringApplication.run(LogosShopApplication.class, args);
	}

	@Override
	public void run(String... args) {
		for (int i = 1; i < 11; i++) {
			TestEntity entity = new TestEntity();
			entity.setName("name" + i);
			testService.create(modelMapperUtil.map(entity, TestDTO.class));
		}
	}
}