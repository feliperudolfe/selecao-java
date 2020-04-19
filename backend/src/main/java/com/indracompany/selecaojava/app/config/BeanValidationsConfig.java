package com.indracompany.selecaojava.app.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.indracompany.selecaojava")
public class BeanValidationsConfig {

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
	    MethodValidationPostProcessor mvProcessor = new MethodValidationPostProcessor();
	    mvProcessor.setValidator(validator());
	    return mvProcessor;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
	    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	    validator.setProviderClass(HibernateValidator.class);
	    validator.afterPropertiesSet();
	    return validator;
	}
}