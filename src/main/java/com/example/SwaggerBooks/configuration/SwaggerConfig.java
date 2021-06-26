package com.example.SwaggerBooks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Books Rest API")
                .description("Restful Api for Books from Google APIs")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket bookApi() {
        System.out.println("inside method");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }
}
