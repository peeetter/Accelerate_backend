package com.accelerate.web;

import com.accelerate.web.TestData.TestData;
import org.junit.jupiter.api.Test;
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
