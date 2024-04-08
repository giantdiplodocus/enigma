package com.giantdiplodocus.enigma;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnigmaApplication{
    public static void main(String[] args) {
        SpringApplication.run(EnigmaApplication.class,args);
    }

}