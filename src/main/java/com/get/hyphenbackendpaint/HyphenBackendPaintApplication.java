package com.get.hyphenbackendpaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HyphenBackendPaintApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyphenBackendPaintApplication.class, args);
    }

}
