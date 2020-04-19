package com.indracompany.selecaojava.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
    		.select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData())
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, responseMessageForGET());
    }

	private List<ResponseMessage> responseMessageForGET() {

		List<ResponseMessage> retorno = new ArrayList<>();

		retorno.add(new ResponseMessageBuilder()
	            .code(500)
	            .message("500 message")
	            .responseModel(new ModelRef("Error"))
	            .build());

		retorno.add(new ResponseMessageBuilder()
	            .code(403)
	            .message("Forbidden!")
	            .build());

	    return retorno;
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
			.title("Seleção Java - Indra Company")
			.description("Atividade de seleção para o cargo de Desenvolvedor Sênior na Indra Company")
			.contact(new Contact("Felipe Rudolfe", "", "feliperudolfe@outlook.com"))
			.version("1.0.0")
			.license("Apache License Version 2.0")
			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
			.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		super.addResourceHandlers(registry);
	}
}