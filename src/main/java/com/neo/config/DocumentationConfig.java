package com.neo.config;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"prod", "dev"})
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
/*
Kudos to: http://fizzylogic.nl/2015/07/29/quickly-generate-api-docs-for-your-spring-boot-application-using-springfox/
 */
public class DocumentationConfig {
    private static String title = "NASA NEO";
    private static String description = "Rest API to access information on Near Earth Objects";
    private static String version = "v1 - BETA";
    private static String termsOfServiceUrl = "Free usage. If you wish, feel free to drop an email to contact us.";
    private static String contact = "NEOMachineLearning@spaceappschallenge.org";
    private static String license = "Apache 2.0";
    private static String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

    @Bean
    public Docket documentation() {
        //Swagger enable: Kudos to: http://stackoverflow.com/questions/27442300/disabling-swagger-with-spring-mvc
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("NASA NEO API")
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/rest/**"))
                .build()
                .apiInfo(metadata());
    }

    @VisibleForTesting
    ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(contact)
                .termsOfServiceUrl(termsOfServiceUrl)
                .build();
    }
}
