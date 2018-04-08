package com.cloudcompilerr;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CloudCompilerrLoggingApplication {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
	new SpringApplicationBuilder(CloudCompilerrLoggingApplication.class).web(false).build().run(args);
    }
}