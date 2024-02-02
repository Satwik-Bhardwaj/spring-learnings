package com.learnings.springcore.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // it makes object prototype, means new object for each injection
public class TennisCoach implements Coach{

    TennisCoach() {
        System.out.println("Tennis Coach object created.");
    }
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
