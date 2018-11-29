package com.admin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fengxiang
 * @date 2018-07-19
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${enableApi}")
public class Swagger2Config {

	/**
	 * 自动生成swagger api
	 * @return
	 */
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
		                //页面标题
		                .title(" API")
		                //版本号
		                .version("1.0")
		                .build())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.admin.controller")) 
				.paths(PathSelectors.any()) 
				.build();
	}
}
