package com.indracompany.selecaojava.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}