package com.indracompany.selecaojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import com.indracompany.selecaojava.app.config.FileStorageProperties;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@EnableAsync
@EnableAutoConfiguration
@EnableConfigurationProperties({ FileStorageProperties.class })
@SpringBootApplication(scanBasePackages = "com.indracompany.selecaojava")
public class SelecaoJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelecaoJavaApplication.class, args);
	}

}