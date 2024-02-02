package com.learnings.springcore.config;

import com.learnings.springcore.common.Coach;
import com.learnings.springcore.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewConfiguration {

    @Bean ("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }

}
