package com.accelerate.web;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
class WebApplicationTests {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
