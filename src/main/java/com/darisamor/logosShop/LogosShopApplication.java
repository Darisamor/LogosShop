package com.darisamor.logosShop;

import com.darisamor.logosShop.domain.TestDTO;
import com.darisamor.logosShop.entity.TestEntity;
import com.darisamor.logosShop.service.TestService;
import com.darisamor.logosShop.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
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

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/**"))
				.apis(RequestHandlerSelectors.basePackage("com.darisamor.logosShop"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
	    return new ApiInfo(
	      "Logos shop API", "API for final logos project",
                "0.1", "some.terms.of.service.url",
                new springfox.documentation.service.Contact("Darisamor", "", "NasarHONDA@gmail.com"),
                "API License", "http://some.license.url",
                Collections.emptyList()
        );
    }
}