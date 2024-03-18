/**
 * The HrmsApplication class is the entry point for the HRMS application.
 * It uses Spring Boot to configure and run the application.
 */
package com.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrmsApplication {

    /**
     * The main method is the entry point for the HRMS application.
     * It starts the Spring Boot application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }

}