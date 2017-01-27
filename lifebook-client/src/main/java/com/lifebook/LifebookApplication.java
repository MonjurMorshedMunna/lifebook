package com.lifebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by monju on 27-Jan-17.
 */
@SpringBootApplication
public class LifebookApplication {

    private static final Logger log = LoggerFactory.getLogger(LifebookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LifebookApplication.class);
    }
}
